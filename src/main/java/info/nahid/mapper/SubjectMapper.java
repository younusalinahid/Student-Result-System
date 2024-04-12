package info.nahid.mapper;

import info.nahid.dto.SemesterDTO;
import info.nahid.dto.SubjectDTO;
import info.nahid.entity.Semester;
import info.nahid.entity.Subject;
import info.nahid.request.SemesterRequest;
import info.nahid.request.SubjectRequest;

import java.util.ArrayList;
import java.util.List;

public class SubjectMapper {

    private SubjectMapper() {

    }

    public static Subject convertSubjectRequestWithoutId(Long semesterId, SubjectRequest subjectRequest) {
        Semester semester = new Semester();
        semester.setId(semesterId);
        return new Subject(null,
                subjectRequest.getName(),
                semester);
    }

    public static Subject convertSubjectRequestWithId(Long semesterId, Long subjectId, SubjectRequest subjectRequest) {
        Semester semester = new Semester();
        semester.setId(semesterId);
        return new Subject(
                subjectId,
                subjectRequest.getName(),
                semester);
    }

    public static SubjectDTO convertSubjectWithoutId(Subject subject) {
        return new SubjectDTO(
                subject.getId(),
                subject.getName(),
                subject.getGPA()
        );

    }


}
