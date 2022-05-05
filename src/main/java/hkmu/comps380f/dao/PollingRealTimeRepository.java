/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.dao;

import hkmu.comps380f.model.PollingRealTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PollingRealTimeRepository extends JpaRepository<PollingRealTime, Long> {

    @Query(value = "select * from pollingrealtime  where username = ?1 and question = ?2", nativeQuery = true)
    public PollingRealTime findByUserNameAndQuestion(String username, String question);
}
