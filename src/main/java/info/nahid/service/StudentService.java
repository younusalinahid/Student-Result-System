package info.nahid.service;

import info.nahid.dto.StudentResultDTO;
import info.nahid.entity.Student;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.request.StudentSemesterRequest;
import info.nahid.request.StudentSubjectResultRequest;
import java.util.List;
import java.util.Map;

public interface StudentService {

    Student create(Student student) throws ConstraintsViolationException;

    Student getById(Long id);

    Student update(Student student) throws ConstraintsViolationException;

    List<Student> getStudentsByGender(String gender);

    List<Student> getStudentsByYear(int year);

    List<Student> getStudentsByCompleteBachelor(boolean completedBachelor);

    List<Student> getStudentsBySemesterId(Long semesterId);

    List<Student> getStudentsByDepartmentId(Long departmentId);

    void saveSemesterInStudent(StudentSemesterRequest request);

    List<Student> getAllStudents();

    void deleteById(Long id);

    Map<String, Object> getStudentResultWithGPA(StudentSubjectResultRequest request);

    StudentResultDTO getStudentResults(Long studentId);

}
