package info.nahid.service;

import info.nahid.entity.Department;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.repository.DepartmentRepository;
import info.nahid.utils.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import javax.validation.ConstraintViolationException;

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
        return null;
    }

    @Override
    public Department update(Department department) throws ConstraintViolationException {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
