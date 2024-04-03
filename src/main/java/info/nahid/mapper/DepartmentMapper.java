package info.nahid.mapper;

import info.nahid.dto.DepartmentDTO;
import info.nahid.entity.Department;
import info.nahid.request.DepartmentRequest;

public class DepartmentMapper {

    private DepartmentMapper() {}

    public static Department convertDepartmentRequestWithoutId(DepartmentRequest departmentRequest) {
        return new Department(null,
                departmentRequest.getName()
        );
    }

    public static DepartmentDTO convertDepartmentWithoutStudentDTO(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName()
        );
    }

    public static Department convertDepartmentRequestWithId(
            DepartmentRequest departmentRequest, long id) {
        return new Department(
                id,
                departmentRequest.getName());
    }

}
