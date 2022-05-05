/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.service;

import hkmu.comps380f.dao.PollingRealTimeRepository;
import hkmu.comps380f.model.PollingRealTime;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PollingRealTimeService {

    @Resource
    private PollingRealTimeRepository pollingRealTimeRepo;

    @Transactional
    public void updatePollingRealTime(String username,
            String question, String choice)
            throws IOException {
        PollingRealTime updatedPollingRealTime = pollingRealTimeRepo.findByUserNameAndQuestion(username, question);
        /*if (updatedPollingRealTime == null) {
            throw IOException;
        }*/

        updatedPollingRealTime.setUsername(username);
        updatedPollingRealTime.setQuestion(question);
        updatedPollingRealTime.setChoice(choice);

        pollingRealTimeRepo.save(updatedPollingRealTime);
    }

    @Transactional
    public PollingRealTime getUser(String username,String question) {
        return pollingRealTimeRepo.findByUserNameAndQuestion(username,question);
    }
}
