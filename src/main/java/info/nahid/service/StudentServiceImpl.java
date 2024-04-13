package info.nahid.service;

import info.nahid.dto.ResultDTO;
import info.nahid.dto.StudentResultDTO;
import info.nahid.entity.Result;
import info.nahid.entity.Semester;
import info.nahid.entity.Student;
import info.nahid.entity.Subject;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.repository.ResultRepository;
import info.nahid.repository.StudentRepository;
import info.nahid.request.StudentSemesterRequest;
import info.nahid.request.StudentSubjectResultRequest;
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
    public Map<String, Object> getStudentResultWithGPA(StudentSubjectResultRequest request) {
        Student student = studentService.getById(request.getStudentId());
        Subject subject = subjectService.getById(request.getSubjectId());

        String grade = calculateGrade(request.getMarks());

        Result result = new Result();
        result.setMarks(request.getMarks());
        result.setStudent(student);
        result.setSubject(subject);
        resultRepository.save(result);

        Map<String, Object> response = new HashMap<>();
        response.put("subjectName", subject.getName());
        response.put("grade",grade);

        return response;
    }

    @Override
    public StudentResultDTO getStudentResults(Long studentId) {
        Student student = studentService.getById(studentId);
        List<Result> results = resultRepository.findByStudentId(studentId);

        List<ResultDTO> resultDTOs = new ArrayList<>();
        double totalGPA = 0.0;
        for (Result result : results) {
            String grade = calculateGrade(result.getMarks());

            ResultDTO resultDTO = new ResultDTO();
            resultDTO.setSubjectName(result.getSubject().getName());
            resultDTO.setGrade(grade);
            resultDTOs.add(resultDTO);

            totalGPA += convertGradeToGPA(grade);
        }
        if (!results.isEmpty()) {
            totalGPA /= results.size();
        }

        totalGPA = Double.parseDouble(String.format("%.2f", totalGPA));

        StudentResultDTO studentResultDTO = new StudentResultDTO();
        studentResultDTO.setId(student.getId());
        studentResultDTO.setName(student.getName());
        studentResultDTO.setRollNumber(student.getRollNumber());
        studentResultDTO.setCompletedBachelor(student.isCompletedBachelor());
        studentResultDTO.setGender(student.getGender());
        studentResultDTO.setYear(student.getYear());
        studentResultDTO.setResultDTOS(resultDTOs);
        studentResultDTO.setTotalGPA(totalGPA);

        return studentResultDTO;
    }

    private String calculateGrade(int marks) {
        if (marks >= 80) {
            return "A+";
        } else if (marks >= 70) {
            return "A";
        } else if (marks >= 60) {
            return "B";
        } else if (marks >= 50) {
            return "C";
        } else {
            return "F";
        }
    }

    private double convertGradeToGPA(String grade) {
        return switch (grade) {
            case "A+" -> 4.0;
            case "A" -> 3.5;
            case "B" -> 3.0;
            case "C" -> 2.0;
            case "F" -> 0.0;
            default -> 0.0;
        };
    }

}
