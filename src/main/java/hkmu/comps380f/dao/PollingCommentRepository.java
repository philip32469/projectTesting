/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.dao;

import hkmu.comps380f.model.PollingComment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PollingCommentRepository extends JpaRepository<PollingComment, Long> {
    
}
