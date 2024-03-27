package info.nahid.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "department",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Student> students;
}
