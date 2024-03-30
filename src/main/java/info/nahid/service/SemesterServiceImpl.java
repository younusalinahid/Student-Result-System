package info.nahid.service;

import info.nahid.entity.Semester;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.repository.SemesterRepository;
import info.nahid.utils.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class SemesterServiceImpl implements  SemesterService{

    @Autowired
    SemesterRepository semesterRepository;

    private static Logger logger = LoggerFactory.getLogger(SemesterServiceImpl.class);

    @Override
    public Semester create(Semester semester) throws ConstraintsViolationException {
        try {
            return semesterRepository.save(semester);
        }catch (DataIntegrityViolationException | ConstraintViolationException exception) {
            logger.warn(Constants.DATA_VIOLATION + exception.getMessage());
            throw new ConstraintsViolationException(Constants.ALREADY_EXISTS);
        }
    }

    @Override
    public Semester getById(Long id) {
        return null;
    }

    @Override
    public Semester update(Semester semester) throws ConstraintsViolationException {
        return null;
    }

    @Override
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

    }
}
