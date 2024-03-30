package info.nahid.repository;

import info.nahid.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByYearAndGender(int year, String gender);

    List<Student> findByYear(int year);

    List<Student> findByGender(String gender);

    List<Student> findByCompletedBachelor(boolean completedBachelor);

}
