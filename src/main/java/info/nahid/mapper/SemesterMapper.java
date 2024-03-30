package info.nahid.mapper;

import info.nahid.dto.SemesterWithoutDTO;
import info.nahid.entity.Semester;
import info.nahid.request.SemesterRequest;

public class SemesterMapper {

    private SemesterMapper() {

    }

    public static Semester convertSemesterRequestWithoutId(SemesterRequest semesterRequest) {
        return new Semester(null,
                semesterRequest.getName());
    }

    public static SemesterWithoutDTO convertSemesterWithoutDto(Semester semester) {
        return new SemesterWithoutDTO(
                semester.getId(),
                semester.getName()
        );
    }

}
