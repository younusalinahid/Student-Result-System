package info.nahid.service;

import info.nahid.entity.Student;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.repository.StudentRepository;
import info.nahid.utils.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;

@Service
public class StudentServiceImpl implements StudentService {

    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @Override
    public Student create(Student student) throws ConstraintsViolationException {
        try {
            return studentRepository.save(student);
        }catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }

    @Override
    public Student getById(Long id) {
        return null;
    }

    @Override
    public Student update(Student student) throws ConstraintViolationException {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
