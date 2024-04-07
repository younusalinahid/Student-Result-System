package info.nahid.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int totalMark;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;



    @JsonManagedReference
    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Grade> grades;

    public Subject(Long id, String name, int totalMark, Semester semester) {
        this.id = id;
        this.name = name;
        this.totalMark = totalMark;
        this.semester = semester;
    }
}
