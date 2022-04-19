package hkmu.comps380f.controller;

import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import hkmu.comps380f.dao.StudentInfoRepository;

import hkmu.comps380f.model.StudentInfo;

@Controller
//@RequestMapping("/ticket")
public class StudentInfoController {

    @Resource
    StudentInfoRepository studentInfoRepository;

    @GetMapping("/registration")
    public ModelAndView Create() {
        return new ModelAndView("registration", "registrationForm", new Form());
    }

    public static class Form {

        private String username;
        private String password;
        private String fullname;
        private String phonenumber;
        private String address;
        private String userrole;

        public String getUserrole() {
            return userrole;
        }

        public void setUserrole(String userrole) {
            this.userrole = userrole;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    @PostMapping("/registration")
    public View Create(Form form) throws IOException {
        StudentInfo student = new StudentInfo(form.getUsername(), form.getPassword(), form.getFullname(), form.getPhonenumber(), form.getAddress(), form.getUserrole());
        studentInfoRepository.save(student);
        return new RedirectView("cslogin", true);
    }
}
