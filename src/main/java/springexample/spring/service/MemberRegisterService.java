package springexample.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springexample.spring.dao.MemberDao;
import springexample.spring.exception.DuplicateMemberException;
import springexample.spring.model.Member;
import springexample.spring.exception.WrongIdPasswordException;

import java.time.LocalDateTime;

@Component
public class MemberRegisterService {

    private MemberDao memberDao;

    public MemberRegisterService() {
    }

    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Transactional
    public Long regist(RegisterRequest request) {
        Member member = memberDao.selectByEmail(request.getEmail());

        if (member != null) throw new DuplicateMemberException("Duplicated email " + request.getEmail());
        if (!request.isPasswordEqualToConfirmPassword()) throw new WrongIdPasswordException();

        Member newMember =
                new Member(request.getEmail(), request.getPassword(), request.getName(), LocalDateTime.now());
        memberDao.insert(newMember);
        return newMember.getId();
    }
}
