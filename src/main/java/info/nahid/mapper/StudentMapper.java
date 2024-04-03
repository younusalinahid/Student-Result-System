package info.nahid.mapper;

import info.nahid.dto.StudentDto;
import info.nahid.dto.StudentInfoDTO;
import info.nahid.entity.Department;
import info.nahid.entity.Student;
import info.nahid.request.StudentRequest;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<StudentInfoDTO> convertStudentsWithDepartmentAndSemester(List<Student> students) {
        return students.stream()
                .map(student -> new StudentInfoDTO(
                        student.getId(),
                        student.getName(),
                        student.getRollNumber(),
                        student.isCompletedBachelor(),
                        student.getGender(),
                        student.getYear(),
                        DepartmentMapper.convertDepartmentWithoutStudentDTO(student.getDepartment()),
                        SemesterMapper.convertSemesterWithoutSubjectDtoList(student.getSemesters())
                ))
                .collect(Collectors.toList());
    }

}
