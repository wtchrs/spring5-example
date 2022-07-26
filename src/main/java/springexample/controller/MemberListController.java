package springexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import springexample.spring.model.Member;
import springexample.spring.dao.MemberDao;

import java.util.List;

@Controller
public class MemberListController {

    private MemberDao memberDao;

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @RequestMapping("/members")
    public String list(@ModelAttribute("listCommand") ListCommand listCommand, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "member/memberList";
        }
        if (listCommand.getFrom() != null && listCommand.getTo() != null) {
            List<Member> members = memberDao.selectByRegdate(listCommand.getFrom(), listCommand.getTo());
            model.addAttribute("members", members);
        }
        return "member/memberList";
    }
}
