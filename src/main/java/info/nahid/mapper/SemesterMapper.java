package info.nahid.mapper;

import info.nahid.dto.SemesterDTO;
import info.nahid.dto.SemesterWithSubjectDTO;
import info.nahid.dto.SubjectDTO;
import info.nahid.entity.Semester;
import info.nahid.entity.Subject;
import info.nahid.request.SemesterRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SemesterMapper {

    private SemesterMapper() {

    }

    public static Semester convertSemesterRequestWithoutId(SemesterRequest semesterRequest) {
        return new Semester(null,
                semesterRequest.getName()
        );
    }

    public static SemesterDTO convertSemesterWithoutDto(Semester semester) {
        return new SemesterDTO(
                semester.getId(),
                semester.getName()
        );
    }

    public static Semester convertSemesterRequestWithId(
            SemesterRequest semesterRequest, Long id) {
        return new Semester(
                id,
                semesterRequest.getName()
                );
    }

    public static List<SemesterDTO> convertSemesterWithoutSubjectDtoList(List<Semester> semesters) {
        List<SemesterDTO> convertedSemesters = new ArrayList<>();
        for (Semester semester : semesters) {
            convertedSemesters.add(convertSemesterWithoutDto(semester));
        }
        return convertedSemesters;
    }

//    public static SemesterWithSubjectDTO convertSemesterWithSubjectDTO(Semester semester) {
//        return new SemesterWithSubjectDTO(
//                semester.getId(),
//                semester.getName(),
//                semester.getSubjects()
//        );
//    }
//
//    public static List<SemesterWithSubjectDTO> convertSemesterWithSubjectDTOList(List<Semester> semesters) {
//        List<SemesterWithSubjectDTO> semesterWithSubject = new ArrayList<>();
//        for (Semester semester : semesters) {
//            semesterWithSubject.add(convertSemesterWithSubjectDTO(semester));
//        }
//        return semesterWithSubject;
//    }


}
