package springexample.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springexample.spring.model.Member;
import springexample.spring.dao.MemberDao;
import springexample.spring.exception.MemberNotFoundException;

@Controller
public class MemberDetailController {

    private MemberDao memberDao;

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @GetMapping("/members/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Member memberById = memberDao.selectById(id);
        if (memberById == null) {
            throw new MemberNotFoundException();
        }
        model.addAttribute("member", memberById);
        return "member/memberDetail";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException() {
        return "member/invalidId";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException() {
        return "member/noMember";
    }
}
