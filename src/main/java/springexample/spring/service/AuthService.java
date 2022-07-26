package springexample.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springexample.spring.dao.MemberDao;
import springexample.spring.model.Member;
import springexample.spring.exception.WrongIdPasswordException;

@Component
public class AuthService {

    private MemberDao memberDao;

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public AuthInfo authenticate(String email, String password) {
        Member member = memberDao.selectByEmail(email);
        if (member == null || !member.matchPassword(password)) {
            throw new WrongIdPasswordException();
        }
        return new AuthInfo(member.getId(), member.getEmail(), member.getName());
    }
}
