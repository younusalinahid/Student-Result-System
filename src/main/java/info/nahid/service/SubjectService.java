package info.nahid.service;

import info.nahid.entity.Subject;
import info.nahid.exception.ConstraintsViolationException;

import java.util.List;

public interface SubjectService {

    Subject create(Subject subject)
            throws ConstraintsViolationException;

    Subject getById(Long id);

    Subject update(Subject subject)
            throws ConstraintsViolationException;

    List<Subject> getAllSubjects();


    void deleteById(Long id);

}
