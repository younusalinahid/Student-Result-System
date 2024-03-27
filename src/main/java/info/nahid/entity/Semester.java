package info.nahid.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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

    public Semester(long id, String name) {
        this.id = id;
        this.name = name;
    }

//    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
//    private List<Student> students;

}
