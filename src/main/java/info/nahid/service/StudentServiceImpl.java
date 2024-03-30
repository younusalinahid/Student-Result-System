package info.nahid.service;

import info.nahid.entity.Student;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.repository.StudentRepository;
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
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DepartmentService departmentService;


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
    public List<Student> getStudentsByYearAndGender(int year, String gender) {
        return studentRepository.findByYearAndGender(year, gender);
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
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
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
}
