package info.nahid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithoutAnyDTO {

    private Long id;
    private String name;
    private int rollNumber;
    private boolean completedBachelor;
    private String gender;
    private int year;

}
