package info.nahid.controller;

import info.nahid.dto.StudentInfoDTO;
import info.nahid.dto.StudentResultDTO;
import info.nahid.repository.ResultRepository;
import info.nahid.request.StudentSubjectResultRequest;
import info.nahid.entity.Student;
import info.nahid.mapper.StudentMapper;
import info.nahid.request.StudentSemesterRequest;
import info.nahid.response.ApiResponse;
import info.nahid.response.ObjectResponse;
import info.nahid.service.SemesterService;
import info.nahid.service.StudentService;
import info.nahid.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    SemesterService semesterService;

    @Autowired
    ResultRepository resultRepository;

    @GetMapping
    public ResponseEntity<ObjectResponse> getAllStudents(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "completedBachelor", required = false) Boolean completedBachelor,
            @RequestParam(value = "semesterId", required = false) Long semesterId,
            @RequestParam(value = "departmentId", required = false) Long departmentId) {

        List<Student> students;

        if (year != null) {
            students = studentService.getStudentsByYear(year);
        } else if (gender != null) {
            students = studentService.getStudentsByGender(gender);
        } else if (completedBachelor != null) {
            students = studentService.getStudentsByCompleteBachelor(completedBachelor);
        } else if (semesterId != null) {
            students = studentService.getStudentsBySemesterId(semesterId);
        } else if (departmentId != null) {
            students = studentService.getStudentsByDepartmentId(departmentId);
        } else {
            students = studentService.getAllStudents();
        }
        ObjectResponse response = new ObjectResponse(true, "Success", students);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/enrollment")
    public ResponseEntity<ApiResponse> saveSemestersInStudents(@RequestBody StudentSemesterRequest studentSemesterRequest) {
        studentService.saveSemesterInStudent(studentSemesterRequest);
        return ResponseEntity.ok().body(new ApiResponse(true,"success"));
    }



    @GetMapping("/{id}/info")
    public ResponseEntity<ObjectResponse> getStudentForInfo(@PathVariable Long id) {
        Student student = studentService.getById(id);
        StudentInfoDTO studentsInfo = StudentMapper.convertStudentsWithDepartmentAndSemester(student);
        return ResponseEntity.ok(new ObjectResponse(true, Constants.STUDENT_FOUND, studentsInfo));
    }

    @PostMapping("/result")
    public ResponseEntity<ObjectResponse> saveResultForSubject(@RequestBody StudentSubjectResultRequest request) {
        Map<String, Object> result = studentService.getStudentResultWithGPA(request);
        return ResponseEntity.ok().body(new ObjectResponse(true,"Student ", result));
    }

    @GetMapping("/{studentId}/result")
    public ResponseEntity<StudentResultDTO> getStudentResultWithGPA(@PathVariable Long studentId) {
        StudentResultDTO studentResultDTO = studentService.getStudentResults(studentId);
        return ResponseEntity.ok().body(studentResultDTO);
    }

}