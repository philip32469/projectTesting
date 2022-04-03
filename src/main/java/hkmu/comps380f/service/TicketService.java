package hkmu.comps380f.service;

import hkmu.comps380f.dao.AttachmentRepository;
import hkmu.comps380f.dao.TicketRepository;
import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.TicketNotFound;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.Ticket;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TicketService {

    @Resource
    private TicketRepository ticketRepo;

    @Resource
    private AttachmentRepository attachmentRepo;

    @Transactional
    public List<Ticket> getTickets() {
        return ticketRepo.findAll();
    }

    @Transactional
    public Ticket getTicket(long id) {
        return ticketRepo.findById(id).orElse(null);
    }

    @Transactional(rollbackFor = TicketNotFound.class)
    public void delete(long id) throws TicketNotFound {
        Ticket deletedTicket = ticketRepo.findById(id).orElse(null);
        if (deletedTicket == null) {
            throw new TicketNotFound();
        }
        ticketRepo.delete(deletedTicket);
    }

    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteAttachment(long ticketId, String name) throws AttachmentNotFound {
        Ticket ticket = ticketRepo.findById(ticketId).orElse(null);
        for (Attachment attachment : ticket.getAttachments()) {
            if (attachment.getName().equals(name)) {
                ticket.deleteAttachment(attachment);
                ticketRepo.save(ticket);
                return;
            }
        }
        throw new AttachmentNotFound();
    }

    @Transactional
    public long createTicket(String customerName, String subject,
            String body, List<MultipartFile> attachments) throws IOException {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(customerName);
        ticket.setSubject(subject);
        ticket.setBody(body);

        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setTicket(ticket);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                ticket.getAttachments().add(attachment);
            }
        }
        Ticket savedTicket = ticketRepo.save(ticket);
        return savedTicket.getId();
    }

    @Transactional(rollbackFor = TicketNotFound.class)
    public void updateTicket(long id, String subject,
            String body, List<MultipartFile> attachments)
            throws IOException, TicketNotFound {
        Ticket updatedTicket = ticketRepo.findById(id).orElse(null);
        if (updatedTicket == null) {
            throw new TicketNotFound();
        }

        updatedTicket.setSubject(subject);
        updatedTicket.setBody(body);

        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setTicket(updatedTicket);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                updatedTicket.getAttachments().add(attachment);
            }
        }
        ticketRepo.save(updatedTicket);
    }
}
