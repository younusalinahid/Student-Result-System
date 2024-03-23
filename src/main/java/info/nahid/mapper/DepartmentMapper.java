package info.nahid.mapper;

import info.nahid.dto.DepartmentWithoutStudentDTO;
import info.nahid.entity.Department;
import info.nahid.request.DepartmentRequest;


public class DepartmentMapper {

    private DepartmentMapper() {}

    public static Department convertDepartmentRequestWithoutId(DepartmentRequest departmentRequest) {
        return new Department(0,
                departmentRequest.getName()
        );
    }

    public static DepartmentWithoutStudentDTO convertDepartmentWithoutStudentDTO(Department department) {
        return new DepartmentWithoutStudentDTO(
                department.getId(),
                department.getName()
        );
    }

}
