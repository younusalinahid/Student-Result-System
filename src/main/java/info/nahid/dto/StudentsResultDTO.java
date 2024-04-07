package info.nahid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsResultDTO {
    private Long id;
    private String name;
    private int rollNumber;
    private boolean completedBachelor;
    private String gender;
    private int year;
    private List<SemesterDTO> semester;
}
