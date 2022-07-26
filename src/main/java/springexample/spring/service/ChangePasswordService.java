package springexample.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springexample.spring.model.Member;
import springexample.spring.dao.MemberDao;
import springexample.spring.exception.MemberNotFoundException;

import java.sql.SQLException;

@Component
public class ChangePasswordService {

    private MemberDao memberDao;

    @Transactional(rollbackFor = SQLException.class)
    public void changePassword(String email, String oldPwd, String newPwd) {
        Member member = memberDao.selectByEmail(email);
        if (member == null) throw new MemberNotFoundException();

        member.changePassword(oldPwd, newPwd);
        memberDao.update(member);
    }

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
