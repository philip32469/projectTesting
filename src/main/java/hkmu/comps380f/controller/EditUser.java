/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.controller;

import hkmu.comps380f.dao.StudentInfoRepository;
import hkmu.comps380f.service.StudentInfoService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import hkmu.comps380f.model.StudentInfo;
@Controller
@RequestMapping("/editUser")
public class EditUser {

    @Resource
    StudentInfoRepository studentInfoRepository;
//---------------------------------------------------

//=================4/5=======================
    @Autowired
    private StudentInfoService studentInfoService;
    @GetMapping({""})
    public String userList(ModelMap model) {
        model.addAttribute("userDatabase",  studentInfoService.getUserList());
        //model.addAttribute("lectureDatabase", lectureListService.getLectureList());
        return "editUser";
    }
    
}
