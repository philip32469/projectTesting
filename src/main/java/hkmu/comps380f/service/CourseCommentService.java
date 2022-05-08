/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.service;

import hkmu.comps380f.dao.CourseCommentRepository;
import hkmu.comps380f.model.CourseComment;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseCommentService {

    @Resource
    private CourseCommentRepository courseCommentRepo;

    @Transactional
    public void deleteCourseComment(long id) {
        CourseComment deletedComment = courseCommentRepo.findById(id);
        /*if (deletedTicket == null) {
            throw new TicketNotFound();
        }*/
        courseCommentRepo.delete(deletedComment);
    }
}
