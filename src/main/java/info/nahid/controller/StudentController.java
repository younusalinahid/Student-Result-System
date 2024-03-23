package info.nahid.controller;

import info.nahid.entity.Student;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.mapper.StudentMapper;
import info.nahid.request.StudentRequest;
import info.nahid.response.ObjectResponse;
import info.nahid.service.DepartmentService;
import info.nahid.service.StudentService;
import info.nahid.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    DepartmentService departmentService;

//    @PostMapping
//    public ResponseEntity<ObjectResponse> create(@Valid @RequestBody StudentRequest studentRequest) throws ConstraintsViolationException {
//        Student student = StudentMapper.convertStudentRequest(studentRequest);
//        return new ResponseEntity<>(new ObjectResponse(true, Constants.STUDENT_CREATE, StudentMapper
//                .convertStudentWithoutDto(studentService.create(student))),
//                HttpStatus.CREATED);
//    }

}
