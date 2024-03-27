package info.nahid.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private int rollNumber;
    private boolean completedBachelor;
    private String gender;
    private int year;

    public Student(Long id, String name, int rollNumber, boolean completedBachelor, String gender,int year) {
        this.id = id;
        this.name = name;
        this.rollNumber = rollNumber;
        this.completedBachelor = completedBachelor;
        this.gender = gender;
        this.year = year;

    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
