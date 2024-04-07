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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String result;

    public Semester(Long id, String name, String result) {
        this.id = id;
        this.name = name;
        this.result = result;
    }

    @JsonManagedReference
    @ManyToMany(mappedBy = "semesters")
    private List<Student> students = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Subject> subjects;

}
