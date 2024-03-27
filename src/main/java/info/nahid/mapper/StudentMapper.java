package info.nahid.mapper;

import info.nahid.entity.Department;
import info.nahid.entity.Student;
import info.nahid.request.StudentRequest;

public class StudentMapper {

    private StudentMapper() {

    }

    public static Student convertStudentRequest(Long departmentId, StudentRequest studentRequest) {
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

}
