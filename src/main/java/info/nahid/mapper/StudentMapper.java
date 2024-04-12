package info.nahid.mapper;

import info.nahid.dto.*;
import info.nahid.entity.Department;
import info.nahid.entity.Result;
import info.nahid.entity.Semester;
import info.nahid.entity.Student;
import info.nahid.request.StudentRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static info.nahid.mapper.SubjectMapper.convertSubjectWithoutId;

public class StudentMapper {

    private StudentMapper() {

    }

    public static Student convertStudentRequestWithoutId(Long departmentId, StudentRequest studentRequest) {
        Department department = new Department();
        department.setId(departmentId);
        return new Student(null,
                studentRequest.getName(),
                studentRequest.getRollNumber(),
                studentRequest.isCompletedBachelor(),
                studentRequest.getGender(),
                studentRequest.getYear(),
                department);
    }

    public static Student convertStudentRequestWithId(Long departmentId, Long studentId, StudentRequest studentRequest) {
        Department department = new Department();
        department.setId(departmentId);
        return new Student(
                studentId,
                studentRequest.getName(),
                studentRequest.getRollNumber(),
                studentRequest.isCompletedBachelor(),
                studentRequest.getGender(),
                studentRequest.getYear(),
                department
        );
    }

    public static StudentInfoDTO convertStudentsWithDepartmentAndSemester(Student student) {
        return new StudentInfoDTO(
                student.getId(),
                student.getName(),
                student.getRollNumber(),
                student.isCompletedBachelor(),
                student.getGender(),
                student.getYear(),
                DepartmentMapper.convertDepartmentWithoutStudentDTO(student.getDepartment()),
                SemesterMapper.convertSemesterWithoutSubjectDtoList(student.getSemesters())
        );
    }

    public static StudentDTO convertStudentDTOWithoutId(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getRollNumber(),
                student.isCompletedBachelor(),
                student.getGender(),
                student.getYear()
        );
    }

//    public static StudentResultDTO convertStudentsResultWithSubject(Student student) {
//        return new StudentResultDTO(
//                student.getId(),
//                student.getName(),
//                student.getRollNumber(),
//                student.isCompletedBachelor(),
//                student.getGender(),
//                student.getYear(),
//                SemesterMapper.convertSemesterWithSubjectDTOList(student.getSemesters())
//                //SubjectMapper.convertSubjectRequestWithoutId(student.getSubject)
//        );
//    }

}
