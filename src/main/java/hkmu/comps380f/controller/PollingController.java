package hkmu.comps380f.controller;

import hkmu.comps380f.dao.PollingRepository;
import hkmu.comps380f.model.Polling;
import hkmu.comps380f.service.LectureListService;
import hkmu.comps380f.service.PollingService;
import java.io.IOException;
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
@RequestMapping("/polling")
public class PollingController {

    @Resource
    PollingRepository pollingRepository;

    @Autowired
    private PollingService pollingService;

//------------測試---------------------------
    @Autowired
    private LectureListService lectureListService;
//---------------------------------------

    @GetMapping({"", "/pindex"})
    public String pollingList(ModelMap model) {
        model.addAttribute("pollingDatabase", pollingService.getPollingList());
//------------要加--------------------------
        model.addAttribute("lectureDatabase", lectureListService.getLectureList());
        return "pindex";
    }

    @GetMapping("/addpolling")
    public ModelAndView addLecture() {
        return new ModelAndView("addPolling", "pollingForm", new Form());
    }

    public static class Form {

        private String question;
        private String option1;
        private String option2;
        private String option3;
        private String option4;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getOption1() {
            return option1;
        }

        public void setOption1(String option1) {
            this.option1 = option1;
        }

        public String getOption2() {
            return option2;
        }

        public void setOption2(String option2) {
            this.option2 = option2;
        }

        public String getOption3() {
            return option3;
        }

        public void setOption3(String option3) {
            this.option3 = option3;
        }

        public String getOption4() {
            return option4;
        }

        public void setOption4(String option4) {
            this.option4 = option4;
        }
    }

    @PostMapping("/addpolling")
    public String Create(Form form) throws IOException {
        long pollingId = pollingService.addPolling(form.getQuestion(), form.getOption1(), form.getOption2(), form.getOption3(), form.getOption4());
        return "redirect:/polling/pindex";
    }
//@GetMapping("/question{pollingId}")

    @GetMapping("/{pollingId}")
    public String viewQuestion(@PathVariable("pollingId") long pollingId, ModelMap model) {

//---------------12:05------------------------
        Polling polling = pollingService.getQuestion(pollingId);
        if (polling == null) {
            return "redirect:/ticket/list";
        }
//------------------------------------------------
        //原本版本:model.addAttribute("pollingDatabase", pollingService.getPollingList());
//test------------------------------------------------------------
//------------------12:05改完好似有用(暫用12:05ver)
        model.addAttribute("pollingDatabase", polling);
        return "pollingPage";
    }

}
