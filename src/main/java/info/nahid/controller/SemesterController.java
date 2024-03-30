package info.nahid.controller;

import info.nahid.entity.Semester;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.mapper.DepartmentMapper;
import info.nahid.mapper.SemesterMapper;
import info.nahid.request.SemesterRequest;
import info.nahid.response.ObjectResponse;
import info.nahid.service.SemesterService;
import info.nahid.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/semesters")
public class SemesterController {

    @Autowired
    SemesterService semesterService;

    @PostMapping
    public ResponseEntity<ObjectResponse> create(@Valid @RequestBody SemesterRequest semesterRequest)
    throws ConstraintsViolationException {
        Semester semester = SemesterMapper.convertSemesterRequestWithoutId(semesterRequest);
        return new ResponseEntity<>(new ObjectResponse(true, Constants.SEMESTER_CREATED,
                SemesterMapper.convertSemesterWithoutDto(semesterService.create(semester))),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ObjectResponse> getAllSemesters() {
        List<Semester> semesters = semesterService.getAllSemesters();
        ObjectResponse response = new ObjectResponse(true, "Success", semesters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
