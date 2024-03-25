package info.nahid.service;

import info.nahid.entity.Department;
import info.nahid.exception.ConstraintsViolationException;

import java.util.List;


public interface DepartmentService {

    Department create(Department department)
            throws ConstraintsViolationException;

    Department getById(Long id);

    Department update(Department department)
            throws ConstraintsViolationException;

    List<Department> getAllDepartment();

    void deleteById(Long id);
}
