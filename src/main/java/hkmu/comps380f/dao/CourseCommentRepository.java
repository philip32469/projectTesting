/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.dao;

import hkmu.comps380f.model.CourseComment;
import hkmu.comps380f.model.LectureList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CourseCommentRepository extends JpaRepository<CourseComment, Long> {

    @Query(value = "select * from coursecomment  where id = ?1", nativeQuery = true)
    public CourseComment findById(long id);
    
}
