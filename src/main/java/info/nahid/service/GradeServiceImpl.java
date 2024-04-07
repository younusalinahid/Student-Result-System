package info.nahid.service;


import info.nahid.entity.Grade;
import info.nahid.entity.Subject;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.repository.GradeRepository;
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
public class GradeServiceImpl implements GradeService{

    private static final Logger logger = LoggerFactory.getLogger(GradeServiceImpl.class);

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    SubjectService subjectService;


    @Override
    public Grade create(Grade grade) throws ConstraintsViolationException {
        try {
            subjectService.getById(grade.getSubject().getId());
            return gradeRepository.save(grade);
        } catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }


    @Override
    public Grade getById(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        if (grade.isPresent()) {
            return grade.get();
        } else {
            logger.warn(Constants.SUBJECT_NOT_FOUND + id);
            throw new EntityNotFoundException(Constants.SUBJECT_NOT_FOUND + id);
        }
    }

    @Override
    public Grade update(Grade grade) throws ConstraintsViolationException {
        Grade updatedGrade = getById(grade.getId());
        subjectService.getById(grade.getSubject().getId());
        BeanUtils.copyProperties(grade, updatedGrade, "id");
        try {
            return gradeRepository.save(updatedGrade);
        } catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }


    @Override
    public void deleteById(Long id) {
        try {
            gradeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            logger.warn(Constants.GRADE_NOT_FOUND + id);
            throw new EntityNotFoundException(Constants.GRADE_NOT_FOUND + id);
        }
    }
}
