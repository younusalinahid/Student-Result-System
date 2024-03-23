package info.nahid.service;

import info.nahid.entity.Student;
import info.nahid.exception.ConstraintsViolationException;

import javax.validation.ConstraintViolationException;

public interface StudentService {

    Student create(Student student) throws ConstraintViolationException, ConstraintsViolationException;

    Student getById(Long id);

    Student update(Student student) throws ConstraintViolationException;

    void deleteById(Long id);

}
