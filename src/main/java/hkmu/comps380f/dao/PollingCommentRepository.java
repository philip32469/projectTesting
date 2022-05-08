/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.dao;

import hkmu.comps380f.model.PollingComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PollingCommentRepository extends JpaRepository<PollingComment, Long> {

    @Query(value = "select * from pollingcomment  where username = ?1", nativeQuery = true)
    public List<PollingComment> findAllByQuestion(String question);


    @Query(value = "select * from pollingcomment  where id = ?1", nativeQuery = true)
    public PollingComment findById(long id);
}
