package info.nahid.request;

import info.nahid.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    @NotNull(message = Constants.NOT_EMPTY_NAME)
    private String name;

    @Positive(message = Constants.ROLL_POSITIVE)
    private int rollNumber;
    private boolean completedBachelor;
    private String gender;
    private int year;

}
