package info.nahid.request;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import info.nahid.entity.Student;
import info.nahid.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {

    @NotNull(message = Constants.NOT_EMPTY_NAME)
    private String name;

}
