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
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/filter")
    public List<Student> getStudentsByYearAndGender(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "completedBachelor", required = false) Boolean completedBachelor) {

        List<Student> students;

        if (year != null && gender != null && completedBachelor != null) {
            students = studentService.getStudentsByYearAndGender(year, gender);
        } else if (year != null) {
            students = studentService.getStudentsByYear(year);
        } else if (gender != null) {
            students = studentService.getStudentsByGender(gender);
        } else if (completedBachelor != null) {
            students = studentService.getStudentsByCompleteBachelor(completedBachelor);
        } else {
            students = studentService.getAllStudents();
        }
        return students;
    }

    @PostMapping("/enrollment")
    public ResponseEntity<ApiResponse> saveSemestersInStudents(@RequestBody StudentSemesterRequest request) {
        studentService.saveSemesterInStudent(request);
        return ResponseEntity.ok().body(new ApiResponse(true,"Student successfully enrolled in semesters"));
    }

    @GetMapping("/info")
    public ResponseEntity<ObjectResponse> getAllStudentsForInfo() {
        List<Student> students = studentService.getAllStudents();
        List<StudentInfoDTO> studentsInfo = StudentMapper.convertStudentsWithDepartmentAndSemester(students);
        return ResponseEntity.ok(new ObjectResponse(true, Constants.STUDENT_FOUND, studentsInfo));
    }

}
