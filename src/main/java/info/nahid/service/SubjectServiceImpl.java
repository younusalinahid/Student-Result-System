package info.nahid.service;


import info.nahid.entity.Subject;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.repository.SubjectRepository;
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
public class SubjectServiceImpl implements SubjectService{

    private static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SemesterService semesterService;


    @Override
    public Subject create(Subject subject) throws ConstraintsViolationException {
        try {
            semesterService.getById(subject.getSemester().getId());
            return subjectRepository.save(subject);
        } catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }


    @Override
    public Subject getById(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            return subject.get();
        } else {
            logger.warn(Constants.SUBJECT_NOT_FOUND + id);
            throw new EntityNotFoundException(Constants.SUBJECT_NOT_FOUND + id);
        }
    }

    @Override
    public Subject update(Subject subject) throws ConstraintsViolationException {
        Subject updatedSubject = getById(subject.getId());
        semesterService.getById(subject.getSemester().getId());
        BeanUtils.copyProperties(subject, updatedSubject, "id");
        try {
            return subjectRepository.save(updatedSubject);
        } catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }


    @Override
    public void deleteById(Long id) {
        try {
            subjectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            logger.warn(Constants.SUBJECT_NOT_FOUND + id);
            throw new EntityNotFoundException(Constants.SEMESTER_NOT_FOUND + id);
        }
    }
}
