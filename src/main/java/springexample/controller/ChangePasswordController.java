package springexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springexample.spring.service.ChangePasswordService;
import springexample.spring.service.AuthInfo;
import springexample.spring.exception.WrongIdPasswordException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePasswordController {

    private ChangePasswordService changePwdSvc;

    @Autowired
    public void setChangePwdSvc(ChangePasswordService changePwdSvc) {
        this.changePwdSvc = changePwdSvc;
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(new ChangePasswordCommandValidator());
    }

    @GetMapping
    public String form(@ModelAttribute("pwdCommand") ChangePasswordCommand pwdCommand, Errors errors) {
        return "edit/changePwdForm";
    }

    @PostMapping
    public String submit(@ModelAttribute("pwdCommand") @Valid ChangePasswordCommand pwdCommand, Errors errors,
                         HttpSession session) {
        if (errors.hasErrors()) {
            return "edit/changePwdForm";
        }
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        try {
            changePwdSvc.changePassword(authInfo.getEmail(), pwdCommand.getCurrentPassword(), pwdCommand.getNewPassword());
            return "edit/changedPwd";
        } catch (WrongIdPasswordException e) {
            errors.rejectValue("currentPassword", "notMatching");
            return "edit/changePwdForm";
        }
    }
}
