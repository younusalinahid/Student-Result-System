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
@RequestMapping("departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<ObjectResponse> create(@Valid @RequestBody DepartmentRequest departmentRequest) throws ConstraintsViolationException {
        Department department = DepartmentMapper.convertDepartmentRequestWithoutId(departmentRequest);
        return new ResponseEntity<>(new ObjectResponse(true, Constants.DEPARTMENT_CREATED,
                DepartmentMapper.convertDepartmentWithoutStudentDTO(departmentService.create(department))),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjectResponse> update(@PathVariable("id") Long id,
                                                     @Valid @RequestBody DepartmentRequest departmentRequest) throws ConstraintsViolationException {
        Department department = DepartmentMapper.convertDepartmentRequestWithId(departmentRequest, id);
        return ResponseEntity.ok(
                new ObjectResponse(true, Constants.DEPARTMENT_UPDATED,
                        DepartmentMapper.convertDepartmentWithoutStudentDTO(departmentService.update(department)))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponse> getById(@PathVariable("id") @Valid long id) {
        Department department = departmentService.getById(id);
        return ResponseEntity.ok(new ObjectResponse(true, Constants.DEPARTMENT_FOUND, department));
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable("id") @Valid long id) {
        departmentService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, Constants.DEPARTMENT_DELETED));
    }

    @PostMapping("/{departmentId}/students")
    public ResponseEntity<ObjectResponse> createStudent(@PathVariable("departmentId") Long departmentId,
                                                        @Valid @RequestBody StudentRequest studentRequest)
        throws ConstraintsViolationException {
        Student student = StudentMapper.convertStudentRequest(departmentId, studentRequest);
        return new ResponseEntity<>(
                new ObjectResponse(true, Constants.STUDENT_CREATED, studentService.create(student)),
                        HttpStatus.CREATED);
    }
}
