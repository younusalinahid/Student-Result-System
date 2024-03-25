package info.nahid.service;

import info.nahid.entity.Department;
import info.nahid.entity.Student;
import info.nahid.exception.ConstraintsViolationException;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface StudentService {

    Student create(Student student) throws ConstraintsViolationException;

    List<Student> getAllStudents();

    Student getById(Long id);

    Student update(Student student) throws ConstraintsViolationException;

    List<Student> getStudentsByYearAndGender(int year, String gender);

    void deleteById(Long id);

    List<Student> getStudentsByGender(String gender);

    List<Student> getStudentsByYear(int year);
}
