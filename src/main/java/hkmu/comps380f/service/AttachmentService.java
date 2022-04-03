package hkmu.comps380f.service;

import hkmu.comps380f.dao.AttachmentRepository;
import hkmu.comps380f.model.Attachment;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttachmentService {

    @Resource
    private AttachmentRepository attachmentRepo;

    @Transactional
    public Attachment getAttachment(long ticketId, String name) {
        return attachmentRepo.findByTicketIdAndName(ticketId, name);
    }
}
