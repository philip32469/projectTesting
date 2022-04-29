/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.service;

import hkmu.comps380f.dao.PollingResultRepository;
import hkmu.comps380f.model.PollingResult;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PollingResultService {


    @Resource
    private PollingResultRepository PollingResultRepo;

    @Transactional
    public PollingResult getQuestionRecord(long id) {
        return PollingResultRepo.findById(id).orElse(null);
    }
    
}
