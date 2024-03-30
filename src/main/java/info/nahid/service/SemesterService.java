package info.nahid.service;

import info.nahid.entity.Department;
import info.nahid.entity.Semester;
import info.nahid.exception.ConstraintsViolationException;

import java.util.List;

public interface SemesterService {

    Semester create(Semester semester)
            throws ConstraintsViolationException;

    Semester getById(Long id);

    Semester update(Semester semester)
            throws ConstraintsViolationException;

    List<Semester> getAllSemesters();

    void deleteById(Long id);

}
