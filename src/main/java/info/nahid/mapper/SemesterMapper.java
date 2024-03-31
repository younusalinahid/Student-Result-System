package info.nahid.mapper;

import info.nahid.dto.SemesterWithoutDTO;
import info.nahid.entity.Department;
import info.nahid.entity.Semester;
import info.nahid.entity.Student;
import info.nahid.request.DepartmentRequest;
import info.nahid.request.SemesterRequest;

public class SemesterMapper {

    private SemesterMapper() {

    }

    public static Semester convertSemesterRequestWithoutId(SemesterRequest semesterRequest) {
        return new Semester(null,
                semesterRequest.getName()
        );
    }

    public static SemesterWithoutDTO convertSemesterWithoutDto(Semester semester) {
        return new SemesterWithoutDTO(
                semester.getId(),
                semester.getName()
        );
    }

    public static Semester convertSemesterRequestWithId(
            SemesterRequest semesterRequest, Long id) {
        return new Semester(
                id,
                semesterRequest.getName());
    }

}
