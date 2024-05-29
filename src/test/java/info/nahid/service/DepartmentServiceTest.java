package info.nahid.service;

import info.nahid.TestData;
import info.nahid.entity.Department;
import info.nahid.exception.ConstraintsViolationException;
import info.nahid.repository.DepartmentRepository;
import info.nahid.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Mock
    DepartmentRepository departmentRepository;

    @Test
    public void createDepartment_successful() throws ConstraintsViolationException {

        Department department = TestData.crateDepartment();
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Department createDepartment = departmentService.create(department);

        assertEquals(department, createDepartment);
        assertEquals(department.getName(), createDepartment.getName());
    }

    @Test
    public void createDepartment_duplicateKey_throwsConstraintsViolationException() {
        Department department = TestData.crateDepartment();

        doThrow(new DataIntegrityViolationException(Constants.ALREADY_EXISTS))
                .when(departmentRepository).save(any(Department.class));
        assertThrows(ConstraintsViolationException.class, () -> departmentService.create(department));
    }

    @Test
    public void getAll_successful() {
        Department department = TestData.crateDepartment();

        List<Department> departments = List.of(department);
        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> departments1 = departmentService.getAllDepartments();
        assertEquals(1, departments1.size());
    }

}