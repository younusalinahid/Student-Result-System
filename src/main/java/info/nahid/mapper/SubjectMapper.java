package info.nahid.mapper;

import info.nahid.entity.Semester;
import info.nahid.entity.Subject;
import info.nahid.request.SubjectRequest;

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

}
