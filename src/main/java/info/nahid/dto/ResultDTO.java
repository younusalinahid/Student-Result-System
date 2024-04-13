package info.nahid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.security.auth.Subject;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {

    private String subjectName;
    private String grade;
}
