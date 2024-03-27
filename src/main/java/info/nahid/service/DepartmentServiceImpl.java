package info.nahid.service;

import info.nahid.entity.Department;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.repository.DepartmentRepository;
import info.nahid.utils.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentRepository departmentRepository;

    private static Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Override
    public Department create(Department department) throws ConstraintsViolationException{
        try {
            return departmentRepository.save(department);
        }catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }

    @Override
    public Department getById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isPresent()) {
            return department.get();
        } else {
            logger.warn(Constants.DEPARTMENT_NOT_FOUND + id);
            throw new EntityNotFoundException(Constants.DEPARTMENT_NOT_FOUND + id);
        }
    }

    @Override
    public Department update(Department department) throws ConstraintViolationException, ConstraintsViolationException {
        Department updatingDepartment = getById(department.getId());
        BeanUtils.copyProperties(department, updatingDepartment, "id");
        try {
            return departmentRepository.save(updatingDepartment);
        } catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }

    @Override
    public List<Department> getAllDepartments() {
            return departmentRepository.findAll();
    }


    @Override
    public void deleteById(Long id) {
        try {
            departmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            logger.warn(Constants.DEPARTMENT_NOT_FOUND + id);
            throw new EntityNotFoundException(Constants.DEPARTMENT_NOT_FOUND + id);
        }
    }
}
