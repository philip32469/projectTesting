/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.controller;

import hkmu.comps380f.dao.CourseCommentRepository;
import hkmu.comps380f.dao.LectureListRepository;
import hkmu.comps380f.model.CourseComment;
import hkmu.comps380f.model.LectureList;
import hkmu.comps380f.service.CourseCommentService;
import hkmu.comps380f.service.LectureListService;
import hkmu.comps380f.service.PollingService;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lecture")
public class LectureController {

    @Resource
    LectureListRepository lectureListRepository;

    @Resource
    CourseCommentRepository courseCommentRepository;

    @Autowired
    private LectureListService lectureListService;

//關鍵
    @Autowired
    private PollingService pollingService;

    @Autowired
    private CourseCommentService courseCommentService;

    @GetMapping({"", "/pindex"})
    public String list(ModelMap model) {
        model.addAttribute("lectureDatabase", lectureListService.getLectureList());

//關鍵
        model.addAttribute("pollingDatabase", pollingService.getPollingList());
        return "pindex";
    }

    @GetMapping("/addlecture")
    public ModelAndView addLecture() {
        return new ModelAndView("addLecture", "lectureForm", new Form());
    }

    public static class Form {

        //比Lecturer用
        private String courseCode;
        private String courseName;
        //comment用
        private String comment;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

    }

    @PostMapping("/addlecture")
    public String Create(Form form) throws IOException {
        String courseCode = lectureListService.addLectureList(form.courseCode, form.courseName);
        return "redirect:/lecture/coursematerial/" + courseCode;
    }

    @GetMapping("/coursematerial/{courseCode}")
    /*public String viewCourse(ModelMap model) {
        model.addAttribute("lectureDatabase", lectureListService.getLectureList());
        return "courseMaterial";
    }*/
    public ModelAndView viewLecture(@PathVariable("courseCode") String coursecode, ModelMap model) {
        LectureList course = lectureListService.getCourse(coursecode);
        List<CourseComment> comment = courseCommentRepository.findAll();
        model.addAttribute("courseCommentDatabase", comment);
        model.addAttribute("courseDatabase", course);
        return new ModelAndView("courseMaterial", "viewlectureForm", new Form());
    }

    @PostMapping(value = "/coursematerial/{courseCode}", params = "comment")
    public String comment(Form form, Principal principal) {

        CourseComment comment = new CourseComment(form.getCourseName(), principal.getName(), form.getComment());
        courseCommentRepository.save(comment);
        return "redirect:/polling/pindex";
    }

    @GetMapping("/delete/{lectureId}")
    public String deleteLecture(@PathVariable("lectureId") String couserCode) {
        LectureList course = lectureListService.getCourse(couserCode);
        lectureListRepository.delete(course);
        return "redirect:/lecture/pindex";
    }

    @GetMapping("/delete/comment/{courseComment.id}")
    public String deleteLectureComment(@PathVariable("courseComment.id") long commentId) {
        courseCommentService.deleteCourseComment(commentId);
        return "redirect:/lecture/pindex";
    }

    @GetMapping("/commenthistory")
    public ModelAndView courseCommentHistory(ModelMap model) {
        List<CourseComment> courseComment = courseCommentRepository.findAll();
        model.addAttribute("courseCommentDatabase", courseComment);
        return new ModelAndView("courseCommentHistory", "courseCommentForm", new Form());
    }

}
