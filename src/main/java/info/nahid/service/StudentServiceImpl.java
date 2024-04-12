package info.nahid.service;

import info.nahid.entity.Result;
import info.nahid.entity.Semester;
import info.nahid.entity.Student;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.repository.ResultRepository;
import info.nahid.repository.StudentRepository;
import info.nahid.request.StudentSemesterRequest;
import info.nahid.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    StudentService studentService;

    @Autowired
    SemesterService semesterService;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    SubjectService subjectService;


    @Override
    public Student create(Student student) throws ConstraintsViolationException {
        try {
            departmentService.getById(student.getDepartment().getId());
            return studentRepository.save(student);
        } catch (DataIntegrityViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }

    @Override
    public Student getById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            logger.warn(Constants.STUDENT_NOT_FOUND + id);
            throw new EntityNotFoundException(Constants.STUDENT_NOT_FOUND + id);
        }
    }

    @Override
    public Student update(Student student) throws ConstraintsViolationException {
        Student updatedStudent = getById(student.getId());
        departmentService.getById(student.getDepartment().getId());
        BeanUtils.copyProperties(student, updatedStudent,"id");
        try {
            return studentRepository.save(updatedStudent);
        } catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }


    @Override
    public List<Student> getStudentsByGender(String gender) {
        return studentRepository.findByGender(gender);
    }

    @Override
    public List<Student> getStudentsByYear(int year) {
        return studentRepository.findByYear(year);
    }

    @Override
    public List<Student> getStudentsByCompleteBachelor(boolean completedBachelor) {
        return studentRepository.findByCompletedBachelor(completedBachelor);
    }

    @Override
    public List<Student> getStudentsBySemesterId(Long semesterId) {
        return studentRepository.findBySemestersId(semesterId);
    }

    @Override
    public List<Student> getStudentsByDepartmentId(Long departmentId) {
        return studentRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void saveSemesterInStudent(StudentSemesterRequest request) {
        Student student = studentService.getById(request.getStudentId());
        List<Semester> semesters = new ArrayList<>();
        for (Long semesterId: request.getSemestersId()) {
            Semester semester = semesterService.getById(semesterId);
            semesters.add(semester);
        }
        student.getSemesters().addAll(semesters);
        studentRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
        try {
            studentRepository.deleteById(id);
        }catch (EmptyResultDataAccessException exception) {
            logger.warn(Constants.STUDENT_NOT_FOUND + id);
            throw new EntityNotFoundException(Constants.STUDENT_NOT_FOUND + id);
        }
    }

    @Override
    public Map<String, Object> getStudentResultWithGPA(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            return null;
        }
        List<Result> results = resultRepository.findByStudentId(studentId);
        double totalGradePoints = 0;
        double totalCredits = 0;

        for (Result result : results) {
            double credits = result.getSubject().getGPA();
            totalCredits += credits;
            totalGradePoints += convertGradeToPoint(result.getGrade()) * credits;
        }

        double totalGPA = totalGradePoints / totalCredits;

        List<Map<String, Object>> resultDetails = new ArrayList<>();
        for (Result result : results) {
            Map<String, Object> resultDetail = new HashMap<>();
            resultDetail.put("subject", result.getSubject().getName());
            resultDetail.put("subject", result.getSubject().getGPA());
            resultDetail.put("grade", result.getGrade());
            resultDetails.add(resultDetail);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("studentId", studentId);
        result.put("name", student.getName());
        result.put("results", results);
        result.put("totalGPA", totalGPA);

        return result;
    }

    private double convertGradeToPoint(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "A-":
                return 3.5;
            case "B+":
                return 3.7;
            case "B":
                return 3.0;
            case "B-":
                return 2.7;
            case "C+":
                return 2.5;
            case "C":
                return 2.0;
            case "C-":
                return 1.7;
            case "D+":
                return 1.3;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                throw new IllegalArgumentException("Invalid grade: " + grade);
        }
    }
}
