package info.nahid;

import info.nahid.entity.Department;
import info.nahid.request.DepartmentRequest;

public class TestData {

    private TestData() {

    }

    public static long departmentId = 1;
    public static final String departmentName = "Department of CSE";

    public static DepartmentRequest creatDepartmentRequest() {
        return new DepartmentRequest(
                departmentName
        );
    }

    public static Department crateDepartment() {
        return new Department(
                departmentId, departmentName
        );
    }
}
