package info.nahid.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int passingMark;
    private String grade;

    public Grade(Long id, int passingMark, String grade) {
        this.id = id;
        this.passingMark = passingMark;
        this.grade = grade;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

}
