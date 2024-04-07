package info.nahid.request;

import info.nahid.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor @AllArgsConstructor
public class GradeRequest {

    private int passingMark;
    @NotNull(message = Constants.NOT_EMPTY_NAME)
    private String grade;

}
