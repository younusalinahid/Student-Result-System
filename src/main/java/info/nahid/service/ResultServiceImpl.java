package info.nahid.service;

import info.nahid.entity.Result;
import info.nahid.entity.Student;
import info.nahid.entity.Subject;
import info.nahid.repository.ResultRepository;
import info.nahid.repository.StudentRepository;
import info.nahid.repository.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService{

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;

    private static final Logger logger = LoggerFactory.getLogger(ResultServiceImpl.class);


    @Override
    public Result getStudentResults(Result result) {
        return null;
    }

    @Override
    public List<Result> getAllResult() {
        return resultRepository.findAll();
    }

    @Override
    public Result getAllStudentsAndSubjects() {
        return null;
    }


}
