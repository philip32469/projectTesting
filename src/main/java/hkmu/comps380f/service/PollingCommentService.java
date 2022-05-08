/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.service;

import hkmu.comps380f.dao.PollingCommentRepository;
import hkmu.comps380f.model.PollingComment;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PollingCommentService {


    @Resource
    private PollingCommentRepository pollingCommentRepo;

    @Transactional
    public List<PollingComment> getCommentList() {
        return pollingCommentRepo.findAll();
    }

    @Transactional
    public List<PollingComment> getCommentList2(String question) {
        return pollingCommentRepo.findAllByQuestion(question);
    }

    @Transactional
    public void deleteComment(long id) {
        PollingComment deletedComment = pollingCommentRepo.findById(id);

        pollingCommentRepo.delete(deletedComment);
    }
    
}
