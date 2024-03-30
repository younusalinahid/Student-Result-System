package info.nahid.controller;

import info.nahid.entity.Student;
import info.nahid.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
