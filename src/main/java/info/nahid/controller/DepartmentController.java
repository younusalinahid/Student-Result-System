package info.nahid.controller;

import info.nahid.entity.Department;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.mapper.DepartmentMapper;
import info.nahid.request.DepartmentRequest;
import info.nahid.response.ObjectResponse;
import info.nahid.service.DepartmentService;
import info.nahid.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ObjectResponse> create(@Valid @RequestBody DepartmentRequest departmentRequest) throws ConstraintsViolationException {
        Department department = DepartmentMapper.convertDepartmentRequestWithoutId(departmentRequest);
        return new ResponseEntity<>(new ObjectResponse(true, Constants.DEPARTMENT_CREATE, DepartmentMapper
                .convertDepartmentWithoutStudentDTO(departmentService.create(department))),
                HttpStatus.CREATED);
    }
}
