package info.nahid.service;

import info.nahid.entity.Grade;
import info.nahid.exception.ConstraintsViolationException;

import java.util.List;

public interface GradeService {

    Grade create(Grade grade)
            throws ConstraintsViolationException;

    Grade getById(Long id);

    Grade update(Grade grade)
            throws ConstraintsViolationException;

    List<Grade> getAllGrades();


    void deleteById(Long id);

}
