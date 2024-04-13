package info.nahid.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    public Subject(long id, String name, Semester semester) {
        this.id = id;
        this.name = name;
        this.semester = semester;
    }
}
