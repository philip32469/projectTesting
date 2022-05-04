/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.service;

import hkmu.comps380f.dao.VotedUserRepository;
import hkmu.comps380f.model.VotedUser;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotedUserService {

    @Resource
    private VotedUserRepository votedUserRepo;

    @Transactional
    public List<VotedUser> getVotedUser() {
        return votedUserRepo.findAll();
    }

}
