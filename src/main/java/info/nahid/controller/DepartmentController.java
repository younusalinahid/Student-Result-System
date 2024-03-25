package info.nahid.controller;

import info.nahid.entity.Department;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.mapper.DepartmentMapper;
import info.nahid.request.DepartmentRequest;
import info.nahid.response.ApiResponse;
import info.nahid.response.ObjectResponse;
import info.nahid.service.DepartmentService;
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

    @PostMapping
    public ResponseEntity<ObjectResponse> create(@Valid @RequestBody DepartmentRequest departmentRequest) throws ConstraintsViolationException {
        Department department = DepartmentMapper.convertDepartmentRequestWithoutId(departmentRequest);
        return new ResponseEntity<>(new ObjectResponse(true, Constants.DEPARTMENT_CREATE,
                DepartmentMapper.convertDepartmentWithoutStudentDTO(departmentService.create(department))),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjectResponse> update(@PathVariable("id") Long id,
                                                     @Valid @RequestBody DepartmentRequest departmentRequest) throws ConstraintsViolationException {
        Department department = DepartmentMapper.convertDepartmentRequestWithId(departmentRequest, id);
        return ResponseEntity.ok(
                new ObjectResponse(true, Constants.DEPARTMENT_UPDATE,
                        DepartmentMapper.convertDepartmentWithoutStudentDTO(departmentService.update(department)))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponse> getById(@PathVariable("id") @Valid long id) {
        Department department = departmentService.getById(id);
        return ResponseEntity.ok(new ObjectResponse(true, Constants.DEPARTMENT_FOUND, department));
    }

    @GetMapping
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable("id") @Valid long id) {
        departmentService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, Constants.DEPARTMENT_DELETE));
    }
}
