package info.nahid.service;

import info.nahid.entity.Semester;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.repository.SemesterRepository;
import info.nahid.utils.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class SemesterServiceImpl implements  SemesterService{

    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    StudentService studentService;

    private static Logger logger = LoggerFactory.getLogger(SemesterServiceImpl.class);

    @Override
    public Semester create(Semester semester) throws ConstraintsViolationException {
        try {
            return semesterRepository.save(semester);
        }catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }

    @Override
    public Semester getById(Long id) {
        Optional<Semester> semester = semesterRepository.findById(id);
        if (semester.isPresent()) {
            return semester.get();
        } else {
            logger.warn(Constants.SEMESTER_NOT_FOUND + id);
            throw new EntityNotFoundException(Constants.SEMESTER_NOT_FOUND + id);
        }
    }

    @Override
    public Semester update(Semester semester) throws ConstraintsViolationException {
        Semester updatingSemester = getById(semester.getId());
        BeanUtils.copyProperties(semester, updatingSemester);
        try {
            return semesterRepository.save(updatingSemester);
        } catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }

    @Override
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        try {
            semesterRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            logger.warn(Constants.SEMESTER_NOT_FOUND + id);
            throw new EntityNotFoundException(Constants.SEMESTER_NOT_FOUND + id);
        }
    }
}
