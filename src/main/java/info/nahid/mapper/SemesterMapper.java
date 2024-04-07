package info.nahid.mapper;

import info.nahid.dto.SemesterDTO;
import info.nahid.entity.Semester;
import info.nahid.request.SemesterRequest;

import java.util.ArrayList;
import java.util.List;

public class SemesterMapper {

    private SemesterMapper() {

    }

    public static Semester convertSemesterRequestWithoutId(SemesterRequest semesterRequest) {
        return new Semester(null,
                semesterRequest.getName(),
                semesterRequest.getResult()
        );
    }

    public static SemesterDTO convertSemesterWithoutDto(Semester semester) {
        return new SemesterDTO(
                semester.getId(),
                semester.getName(),
                semester.getResult()
        );
    }

    public static Semester convertSemesterRequestWithId(
            SemesterRequest semesterRequest, Long id) {
        return new Semester(
                id,
                semesterRequest.getName(),
                semesterRequest.getResult());
    }

    public static List<SemesterDTO> convertSemesterWithoutSubjectDtoList(List<Semester> semesters) {
        List<SemesterDTO> convertedSemesters = new ArrayList<>();
        for (Semester semester : semesters) {
            convertedSemesters.add(convertSemesterWithoutDto(semester));
        }
        return convertedSemesters;
    }

}
