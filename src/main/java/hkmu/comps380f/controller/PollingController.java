package hkmu.comps380f.controller;

import hkmu.comps380f.dao.PollingCommentRepository;
import hkmu.comps380f.dao.PollingRealTimeRepository;
import hkmu.comps380f.dao.PollingRecordRepository;
import hkmu.comps380f.dao.PollingRepository;
import hkmu.comps380f.dao.PollingResultRepository;
import hkmu.comps380f.dao.VotedUserRepository;
import hkmu.comps380f.model.Polling;
import hkmu.comps380f.model.PollingComment;
import hkmu.comps380f.model.PollingRealTime;
import hkmu.comps380f.model.PollingRecord;
import hkmu.comps380f.model.PollingResult;
import hkmu.comps380f.model.VotedUser;

import hkmu.comps380f.service.LectureListService;
import hkmu.comps380f.service.PollingCommentService;
import hkmu.comps380f.service.PollingRealTimeService;
import hkmu.comps380f.service.PollingResultService;
import hkmu.comps380f.service.PollingService;
import hkmu.comps380f.service.VotedUserService;
import java.io.IOException;
import java.security.Principal;

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

//------------------28/4-----------------
    @Resource
    PollingRecordRepository pollingRecordRepository;

    @Resource
    PollingRealTimeRepository pollingRealTimeRepository;

    @Resource
    PollingResultRepository pollingResultRepository;

    @Resource
    PollingCommentRepository pollingCommentRepository;

//------------4/5 試整一人投一次
    @Resource
    VotedUserRepository votedUserRepository;
//---------------------------------------------------

//=================4/5=======================
    @Autowired
    private VotedUserService votedUserService;

    @Autowired
    private PollingRealTimeService pollingRealTimeService;
//===========================================
    @Autowired
    private PollingService pollingService;

    @Autowired
    private PollingResultService pollingResultService;

    @Autowired
    private PollingCommentService pollingCommentService;
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

        private String choice;
        private String questionRecord;

//--------------for comment用-------------------
        private String comment;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getQuestionRecord() {
            return questionRecord;
        }

        public void setQuestionRecord(String questionRecord) {
            this.questionRecord = questionRecord;
        }

        public String getChoice() {
            return choice;
        }

        public void setChoice(String choice) {
            this.choice = choice;
        }

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

//-------------------------試整result-------------------------------------------
        PollingResult result = new PollingResult(form.getQuestion(), 0, 0, 0, 0);
        pollingResultRepository.save(result);
//------------------------------------------------------------------------------------------------------------
        return "redirect:/polling/pindex";
    }
//@GetMapping("/question{pollingId}")

    /*@GetMapping("/{pollingId}")
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
    }*/
    @GetMapping("/{pollingId}")
    public ModelAndView polling(@PathVariable("pollingId") long pollingId, ModelMap model, Principal principal) {
        Polling polling = pollingService.getQuestion(pollingId);
        PollingResult result = pollingResultService.getQuestionRecord(pollingId);
        PollingRealTime recordRealTime = pollingRealTimeService.getUser(principal.getName(), polling.getQuestion());

//-----------------唔知polling list係空呢行有冇用,加住先-------------------------------
        if (polling == null) {
            return new ModelAndView("addPolling", "pollingForm", new Form());
        }
//------------------------------------------------------------------------------------------
        model.addAttribute("pollingDatabase", polling);
        model.addAttribute("pollingResultDatabase", result);
        model.addAttribute("commentDatabase", pollingCommentService.getCommentList());

//=============================4/5========================
        //model.addAttribute("votedUserDatabase", votedUserService.getVotedUser());
        model.addAttribute("currentName", principal.getName());
        model.addAttribute("realTimeDatabase", recordRealTime);
        return new ModelAndView("pollingPage", "pollingForm", new Form());
    }

//========================================================
    @PostMapping(value = "/{pollingId}")
    public String polling(@PathVariable("pollingId") long pollingId, Form form, Principal principal) throws IOException {
        PollingRecord record = new PollingRecord(principal.getName(), form.getQuestionRecord(), form.getChoice());
        pollingRecordRepository.save(record);

        PollingRealTime realTimeRecord = new PollingRealTime(principal.getName(), form.getQuestionRecord(), form.getChoice());
        pollingRealTimeRepository.save(realTimeRecord);

//--------------------------------------------試整一人只可投一次------------------------------
        VotedUser voteduser = new VotedUser(form.getQuestionRecord(), principal.getName());
        votedUserRepository.save(voteduser);

//------------------------未知如果到時有del function 會唔會影響呢到--------------------
        PollingResult updatedResult = pollingResultRepository.findById(pollingId).orElse(null);
        switch (form.getChoice()) {
            case ("A"):
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/
                int totalA = updatedResult.getTotalchoiceA() + 1;
                updatedResult.setTotalchoiceA(totalA);
                pollingResultRepository.save(updatedResult);
                break;
            case ("B"):
                //PollingResult updatedResult = pollingResultRepository.findById((pollingId-2)).orElse(null);
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/

                int totalB = updatedResult.getTotalchoiceB() + 1;
                updatedResult.setTotalchoiceB(totalB);
                pollingResultRepository.save(updatedResult);
                break;
            case ("C"):
                //PollingResult updatedResult = pollingResultRepository.findById((pollingId-2)).orElse(null);
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/

                int totalC = updatedResult.getTotalchoiceC() + 1;
                updatedResult.setTotalchoiceC(totalC);
                pollingResultRepository.save(updatedResult);
                break;
            case ("D"):
                //PollingResult updatedResult = pollingResultRepository.findById((pollingId-2)).orElse(null);
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/

                int totalD = updatedResult.getTotalchoiceD() + 1;
                updatedResult.setTotalchoiceD(totalD);
                pollingResultRepository.save(updatedResult);
                break;
            default:
                break;
        }
        return "redirect:/polling/pindex";
    }
//========================comment===========================================

    @PostMapping(value = "/{pollingId}", params = "comment")
    public String comment(Form form, Principal principal) {

        PollingComment comment = new PollingComment(form.getQuestionRecord(), principal.getName(), form.getComment());
        pollingCommentRepository.save(comment);
        return "redirect:/polling/pindex";
    }
//============================4/5======================

    @GetMapping("/edit/{pollingId}/{principal.getName()}")
    public ModelAndView edit(@PathVariable("pollingId") long pollingId, ModelMap model, Principal principal) {

        Polling polling = pollingService.getQuestion(pollingId);

        PollingRealTime recordRealTime = pollingRealTimeService.getUser(principal.getName(), polling.getQuestion());
        if (recordRealTime == null) {
            model.addAttribute("pollingDatabase", polling);
            return new ModelAndView("redirectToPolling", "redirect", new Form());
        }

        model.addAttribute("currentChoice", recordRealTime.getChoice());

        model.addAttribute("pollingDatabase", polling);
        return new ModelAndView("editPolling", "editForm", new Form());
    }

    @PostMapping("/edit/{pollingId}/{principal.getName()}")
    public String edit(@PathVariable("pollingId") long pollingId, Form form, Principal principal) throws IOException {

        PollingResult updatedResult = pollingResultRepository.findById(pollingId).orElse(null);

        PollingRealTime recordRealTime = pollingRealTimeService.getUser(principal.getName(), form.questionRecord);

        switch (recordRealTime.getChoice()) {
            case ("A"):
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/
                int totalA = updatedResult.getTotalchoiceA() - 1;
                updatedResult.setTotalchoiceA(totalA);
                pollingResultRepository.save(updatedResult);
                break;
            case ("B"):
                //PollingResult updatedResult = pollingResultRepository.findById((pollingId-2)).orElse(null);
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/

                int totalB = updatedResult.getTotalchoiceB() - 1;
                updatedResult.setTotalchoiceB(totalB);
                pollingResultRepository.save(updatedResult);
                break;
            case ("C"):
                //PollingResult updatedResult = pollingResultRepository.findById((pollingId-2)).orElse(null);
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/

                int totalC = updatedResult.getTotalchoiceC() - 1;
                updatedResult.setTotalchoiceC(totalC);
                pollingResultRepository.save(updatedResult);
                break;
            case ("D"):
                //PollingResult updatedResult = pollingResultRepository.findById((pollingId-2)).orElse(null);
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/

                int totalD = updatedResult.getTotalchoiceD() - 1;
                updatedResult.setTotalchoiceD(totalD);
                pollingResultRepository.save(updatedResult);
                break;
            default:
                break;
        }

        pollingRealTimeService.updatePollingRealTime(principal.getName(), form.getQuestionRecord(), form.getChoice());

        switch (form.getChoice()) {
            case ("A"):
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/
                int totalA = updatedResult.getTotalchoiceA() + 1;
                updatedResult.setTotalchoiceA(totalA);
                pollingResultRepository.save(updatedResult);
                break;
            case ("B"):
                //PollingResult updatedResult = pollingResultRepository.findById((pollingId-2)).orElse(null);
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/

                int totalB = updatedResult.getTotalchoiceB() + 1;
                updatedResult.setTotalchoiceB(totalB);
                pollingResultRepository.save(updatedResult);
                break;
            case ("C"):
                //PollingResult updatedResult = pollingResultRepository.findById((pollingId-2)).orElse(null);
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/

                int totalC = updatedResult.getTotalchoiceC() + 1;
                updatedResult.setTotalchoiceC(totalC);
                pollingResultRepository.save(updatedResult);
                break;
            case ("D"):
                //PollingResult updatedResult = pollingResultRepository.findById((pollingId-2)).orElse(null);
                /*if (updatedResult == null) {
                    throw new TicketNotFound();
                }*/

                int totalD = updatedResult.getTotalchoiceD() + 1;
                updatedResult.setTotalchoiceD(totalD);
                pollingResultRepository.save(updatedResult);
                break;
            default:
                break;
        }

        PollingRecord record = new PollingRecord(principal.getName(), form.getQuestionRecord(), form.getChoice());
        pollingRecordRepository.save(record);
        return "redirect:/polling/pindex";
    }

}
