package info.nahid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResultDTO {

    private Long id;
    private String name;
    private int rollNumber;
    private boolean completedBachelor;
    private String gender;
    private int year;
    List<ResultDTO> resultDTOS;
    private double totalGPA;
}
