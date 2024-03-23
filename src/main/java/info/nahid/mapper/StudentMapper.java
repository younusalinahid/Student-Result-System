package info.nahid.mapper;

import info.nahid.dto.StudentWithoutAnyDTO;
import info.nahid.entity.Student;
import info.nahid.request.StudentRequest;

public class StudentMapper {

    private StudentMapper() {

    }

    public static Student convertStudentRequest(StudentRequest studentRequest) {
        return new Student(null,
                studentRequest.getName(),
                studentRequest.getRollNumber(),
                studentRequest.isCompletedBachelor(),
                studentRequest.getGender(),
                studentRequest.getYear()
        );
    }

    public static StudentWithoutAnyDTO convertStudentWithoutDto(Student student) {
        return new StudentWithoutAnyDTO(
                student.getId(),
                student.getName(),
                student.getRollNumber(),
                student.isCompletedBachelor(),
                student.getGender(),
                student.getYear()
        );
    }

}
