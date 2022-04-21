package hkmu.comps380f.service;

import hkmu.comps380f.dao.PollingRepository;
import hkmu.comps380f.model.Polling;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PollingService {

    @Resource
    private PollingRepository PollingRepo;

    @Transactional
    public List<Polling> getPollingList() {
        return PollingRepo.findAll();
    }


//-----------------12:05 Testing--------------
//想試拎到Question id
    @Transactional
    public Polling getQuestion(long id) {
        return PollingRepo.findById(id).orElse(null);
    }
//----------------------------------------------------

    @Transactional
    public long addPolling(String question, String option1,
            String option2, String option3, String option4) throws IOException {
        Polling polling = new Polling();
        polling.setQuestion(question);
        polling.setOption1(option1);
        polling.setOption2(option2);
        polling.setOption3(option3);
        polling.setOption4(option4);

        Polling savedPolling = PollingRepo.save(polling);
        return savedPolling.getId();
    }
}
