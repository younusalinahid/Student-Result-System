package info.nahid.controller;

import info.nahid.entity.Semester;
import info.nahid.entity.Subject;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.mapper.SemesterMapper;
import info.nahid.mapper.SubjectMapper;
import info.nahid.request.SemesterRequest;
import info.nahid.request.SubjectRequest;
import info.nahid.response.ApiResponse;
import info.nahid.response.ObjectResponse;
import info.nahid.service.SemesterService;
import info.nahid.service.SubjectService;
import info.nahid.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/semesters")
public class SemesterSubjectController {

    @Autowired
    SemesterService semesterService;

    @Autowired
    SubjectService subjectService;

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

    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponse> getById(@PathVariable("id") @Valid Long id) {
        Semester semester = semesterService.getById(id);
        return ResponseEntity.ok(new ObjectResponse(true, Constants.SEMESTER_FOUND, semester));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjectResponse> update(@PathVariable("id") Long id,
                                                 @Valid @RequestBody SemesterRequest semesterRequest) throws ConstraintsViolationException {
        Semester semester = SemesterMapper.convertSemesterRequestWithId(semesterRequest, id);
        return ResponseEntity.ok(
                new ObjectResponse(true, Constants.SEMESTER_UPDATED,
                        SemesterMapper.convertSemesterWithoutDto(semesterService.update(semester)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable("id") @Valid Long id) {
        semesterService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, Constants.SEMESTER_DELETED));
    }

    @PostMapping("/{semesterId}/subjects")
    public ResponseEntity<ObjectResponse> createSubject(@PathVariable("semesterId") Long semesterId,
                                                        @Valid @RequestBody SubjectRequest subjectRequest)
            throws ConstraintsViolationException {
        Subject subject = SubjectMapper.convertSubjectRequestWithoutId(semesterId, subjectRequest);
        return new ResponseEntity<>(
                new ObjectResponse(true, Constants.SUBJECT_CREATED, subjectService.create(subject)),
                HttpStatus.CREATED);
    }

    @GetMapping("/subjects")
    public ResponseEntity<ObjectResponse> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        ObjectResponse response = new ObjectResponse(true, "Success", subjects);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{semesterId}/subjects/{subjectId}")
    public ResponseEntity<ObjectResponse> getSubjectById(@PathVariable("semesterId") Long semesterId,
                                                         @PathVariable("subjectId") Long subjectId) {
        semesterService.getById(semesterId);
        Subject subject = subjectService.getById(subjectId);
        return ResponseEntity.ok(new ObjectResponse(true, Constants.SUBJECT_FOUND, subject));
    }

    @PutMapping("/{semesterId}/subjects/{subjectId}")
    public ResponseEntity<ObjectResponse> updateSubject(@PathVariable("semesterId") Long semesterId,
                                                        @PathVariable("subjectId") Long subjectId,
                                                        @Valid @RequestBody SubjectRequest subjectRequest)
            throws ConstraintsViolationException {
        Subject subject = SubjectMapper.convertSubjectRequestWithId(semesterId,subjectId, subjectRequest);
        return ResponseEntity.ok(
                new ObjectResponse(true, Constants.SUBJECT_UPDATED, subjectService.update(subject))
        );
    }

    @DeleteMapping("/{semesterId}/subjects/{subjectId}")
    public ResponseEntity<ApiResponse> deletedSubject(@PathVariable("semesterId") Long semesterId,
                                                        @PathVariable("subjectId") Long subjectId) {
        semesterService.getById(semesterId);
        subjectService.deleteById(subjectId);
        return ResponseEntity.ok(new ApiResponse(true, Constants.SUBJECT_DELETED));
    }

}
