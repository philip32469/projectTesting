
package hkmu.comps380f.dao;

import hkmu.comps380f.model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, String> {

    @Query(value = "select * from student where username = ?1", nativeQuery = true)
    public StudentInfo findByUserName(String username);
}

