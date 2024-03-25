package info.nahid.controller;

import info.nahid.entity.Department;
import info.nahid.entity.Student;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.mapper.DepartmentMapper;
import info.nahid.mapper.StudentMapper;
import info.nahid.request.DepartmentRequest;
import info.nahid.request.StudentRequest;
import info.nahid.response.ApiResponse;
import info.nahid.response.ObjectResponse;
import info.nahid.service.DepartmentService;
import info.nahid.service.StudentService;
import info.nahid.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ObjectResponse> create(@Valid @RequestBody StudentRequest studentRequest) throws ConstraintsViolationException {
        Student student = StudentMapper.convertStudentRequest(studentRequest);
        return new ResponseEntity<>(new ObjectResponse(true, Constants.STUDENT_CREATE, StudentMapper
                .convertStudentWithoutDto(studentService.create(student))),
                HttpStatus.CREATED);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/filter")
    public List<Student> getStudentsByYearAndGender(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "gender", required = false) String gender) {

        List<Student> students;

        if (year != null && gender != null) {
            students = studentService.getStudentsByYearAndGender(year, gender);
        } else if (year != null) {
            students = studentService.getStudentsByYear(year);
        } else if (gender != null) {
            students = studentService.getStudentsByGender(gender);
        } else {
            students = studentService.getAllStudents();
        }

        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponse> getById(@PathVariable("id") @Valid long id) {
        Student student = studentService.getById(id);
        return ResponseEntity.ok(new ObjectResponse(true, Constants.STUDENT_FOUND, student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjectResponse> update(@PathVariable("id") Long id,
                                                 @Valid @RequestBody StudentRequest studentRequest) throws ConstraintsViolationException {
        Student student = StudentMapper.convertStudentRequestWithId(studentRequest, id);
        return ResponseEntity.ok(
                new ObjectResponse(true, Constants.STUDENT_UPDATE,
                        StudentMapper.convertStudentWithoutDto(studentService.update(student)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable("id") @Valid long id) {
        studentService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, Constants.STUDENT_DELETE));
    }

}
