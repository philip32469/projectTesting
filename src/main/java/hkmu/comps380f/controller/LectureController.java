/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.controller;

import hkmu.comps380f.dao.LectureListRepository;
import hkmu.comps380f.service.LectureListService;
import hkmu.comps380f.service.PollingService;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lecture")
public class LectureController {

    @Resource
    LectureListRepository lectureListRepository;

    @Autowired
    private LectureListService lectureListService;

//關鍵
   @Autowired
    private PollingService pollingService;

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
    public String viewCourse(ModelMap model) {
        model.addAttribute("lectureDatabase", lectureListService.getLectureList());
        return "courseMaterial";
    }

}
