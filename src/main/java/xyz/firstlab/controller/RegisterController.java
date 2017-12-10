package xyz.firstlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.firstlab.member.DuplicateMemberException;
import xyz.firstlab.member.MemberRegisterService;
import xyz.firstlab.member.RegisterRequest;
import xyz.firstlab.member.WrongIdPasswordException;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private MemberRegisterService regSvc;

    @Autowired
    public void setRegSvc(MemberRegisterService regSvc) {
        this.regSvc = regSvc;
    }

    @RequestMapping("/step1")
    public String handleStep1() {
        return "register/step1";
    }

    @PostMapping("/step2")
    public String handleStep2(@ModelAttribute("formData") RegisterRequest regReq,
                              @RequestParam(value = "agree", defaultValue = "false") boolean agree) {
        if (!agree) {
            return "register/step1";
        }
        return "register/step2";
    }

    @GetMapping("/step2")
    public String handleStep2Get() {
        return "redirect:/register/step1";
    }

    @PostMapping("/step3")
    public String handleStep3(@ModelAttribute("formData") RegisterRequest regReq) {
        try {
            regSvc.regist(regReq);
            return "register/step3";
        } catch (DuplicateMemberException | WrongIdPasswordException e) {
            return "register/step2";
        }
    }
}
