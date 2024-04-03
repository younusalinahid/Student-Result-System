package info.nahid.service;

import info.nahid.entity.Department;
import info.nahid.entity.Semester;
import info.nahid.entity.Student;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.request.StudentSemesterRequest;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    Student create(Student student) throws ConstraintsViolationException;

    Student getById(Long id);

    Student update(Student student) throws ConstraintsViolationException;

    List<Student> getStudentsByYearAndGender(int year, String gender);

    List<Student> getStudentsByGender(String gender);

    List<Student> getStudentsByYear(int year);

    List<Student> getStudentsByCompleteBachelor(boolean completedBachelor);

    void saveSemesterInStudent(StudentSemesterRequest request);


    List<Student> getAllStudents();

    void deleteById(Long id);

}
