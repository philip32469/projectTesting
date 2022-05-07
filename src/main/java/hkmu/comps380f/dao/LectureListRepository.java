
package hkmu.comps380f.dao;

import hkmu.comps380f.model.LectureList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LectureListRepository extends JpaRepository<LectureList, String> {

    @Query(value = "select * from course where coursecode = ?1", nativeQuery = true)
    public LectureList findByCourseCode(String coursecode);
}
