package info.nahid.service;

import info.nahid.entity.Student;
import info.nahid.exception.ConstraintsViolationException;

import java.util.UUID;

public interface StudentService {

    Student create(Student student) throws ConstraintsViolationException;

    Student getById(Long id);

    Student update(Student student) throws ConstraintsViolationException;


    void deleteById(Long id);

}
