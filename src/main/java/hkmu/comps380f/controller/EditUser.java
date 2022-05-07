/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.controller;

import hkmu.comps380f.dao.StudentInfoRepository;
import hkmu.comps380f.model.StudentInfo;
import hkmu.comps380f.service.StudentInfoService;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
        model.addAttribute("userDatabase", studentInfoService.getUserList());
        //model.addAttribute("lectureDatabase", lectureListService.getLectureList());
        return "editUser";
    }

    @GetMapping("/{username}")
    public ModelAndView polling(@PathVariable("username") String username, ModelMap model) {

        return new ModelAndView("editUserInfo", "editUserInfoForm", new Form());
    }

    @PostMapping("/{username}")
    public String edit(@PathVariable("username") String username, Form form) throws IOException {
        StudentInfo user = studentInfoService.getUser(username);

        studentInfoService.updateUser(username,form.getNewFullname(), form.getNewPhonenumber(), form.getNewAddress(), form.getNewUser_role());

        return "redirect:/editUser";
    }

    @GetMapping("/delete/{username}")
    public String delUser(@PathVariable("username") String username) {
        StudentInfo user = studentInfoService.getUser(username);
        studentInfoRepository.delete(user);
        return "redirect:/editUser";
    }

    public static class Form {

        String newUsername;
        String newFullname;
        String newPhonenumber;
        String newAddress;
        String newUser_role;

        public String getNewUsername() {
            return newUsername;
        }

        public void setNewUsername(String newUsername) {
            this.newUsername = newUsername;
        }

        public String getNewFullname() {
            return newFullname;
        }

        public void setNewFullname(String newFullname) {
            this.newFullname = newFullname;
        }

        public String getNewPhonenumber() {
            return newPhonenumber;
        }

        public void setNewPhonenumber(String newPhonenumber) {
            this.newPhonenumber = newPhonenumber;
        }

        public String getNewAddress() {
            return newAddress;
        }

        public void setNewAddress(String newAddress) {
            this.newAddress = newAddress;
        }

        public String getNewUser_role() {
            return newUser_role;
        }

        public void setNewUser_role(String newUser_role) {
            this.newUser_role = newUser_role;
        }

    }
}
