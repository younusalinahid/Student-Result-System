package info.nahid.controller;

import info.nahid.dto.StudentInfoDTO;
import info.nahid.entity.Student;
import info.nahid.mapper.StudentMapper;
import info.nahid.request.StudentSemesterRequest;
import info.nahid.response.ApiResponse;
import info.nahid.response.ObjectResponse;
import info.nahid.service.StudentService;
import info.nahid.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

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
    public ResponseEntity<ApiResponse> saveSemestersInStudents(@RequestBody StudentSemesterRequest request) {
        studentService.saveSemesterInStudent(request);
        return ResponseEntity.ok().body(new ApiResponse(true,"Student successfully enrolled in semesters"));
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<ObjectResponse> getStudentForInfo(@PathVariable Long id) {
        Student student = studentService.getById(id);
        StudentInfoDTO studentsInfo = StudentMapper.convertStudentsWithDepartmentAndSemester(student);
        return ResponseEntity.ok(new ObjectResponse(true, Constants.STUDENT_FOUND, studentsInfo));
    }

}
