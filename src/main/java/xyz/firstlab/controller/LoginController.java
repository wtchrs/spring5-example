package xyz.firstlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import xyz.firstlab.spring.AuthInfo;
import xyz.firstlab.spring.AuthService;
import xyz.firstlab.spring.WrongIdPasswordException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(new LoginCommandValidator());
    }

    @GetMapping
    public String form(@ModelAttribute("loginCommand") LoginCommand loginCommand) {
        return "login/loginForm";
    }

    @PostMapping
    public String submit(@ModelAttribute("loginCommand") @Valid LoginCommand loginCommand, Errors errors,
                         HttpSession session) {
        if (errors.hasErrors()) {
            return "login/loginForm";
        }
        try {
            AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
            session.setAttribute("authInfo", authInfo);
            return "login/loginSuccess";
        } catch (WrongIdPasswordException e) {
            errors.reject("idPasswordNotMatching");
            return "login/loginForm";
        }
    }
}
