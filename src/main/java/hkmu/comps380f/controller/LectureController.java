/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.controller;

import hkmu.comps380f.dao.LectureListRepository;
import hkmu.comps380f.model.LectureList;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/lecture")
public class LectureController {

    @Resource
    LectureListRepository lectureListRepository;

    @GetMapping({"", "/pindex"})
    public String list() {
        return "pindex";
    }

    @GetMapping("/addlecture")
    public ModelAndView addLecture() {
        return new ModelAndView("addLecture", "lectureForm", new Form());
    }

    public static class Form {

        private String courseCode;
        private String courseName;

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
    public View Create(Form form) throws IOException {
        LectureList lecture = new LectureList(form.getCourseCode(), form.getCourseName());
        lectureListRepository.save(lecture);
        return new RedirectView("coursematerial", true);
    }

    @GetMapping("/coursematerial")
    public String viewCourse() {
        return "courseMaterial";
    }

}
