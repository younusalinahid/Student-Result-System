package info.nahid.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSemesterRequest {

    private Long studentId;
    private List<Long> semestersId;
}
