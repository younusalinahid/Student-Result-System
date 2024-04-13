package info.nahid.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubjectResultRequest {

    private int marks;
    private Long studentId;
    private Long subjectId;

}
