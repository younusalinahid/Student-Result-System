package info.nahid.mapper;

import info.nahid.entity.Grade;
import info.nahid.entity.Subject;
import info.nahid.request.GradeRequest;

public class GradeMapper {

    private GradeMapper() {

    }

    public static Grade convertGradeRequestWithoutId(Long subjectId, GradeRequest gradeRequest) {
        Subject subject = new Subject();
        subject.setId(subjectId);
        return new Grade(null,
                gradeRequest.getPassingMark(),
                gradeRequest.getGrade(),
                subject);
    }

    public static Grade convertGradeRequestWithId(Long subjectId, Long gradeId, GradeRequest gradeRequest) {
        Subject subject = new Subject();
        subject.setId(subjectId);
        return new Grade(
                gradeId,
                gradeRequest.getPassingMark(),
                gradeRequest.getGrade(),
                subject);
    }

}
