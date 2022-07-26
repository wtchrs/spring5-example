package springexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springexample.spring.dao.MemberDao;
import springexample.spring.exception.DuplicateMemberException;
import springexample.spring.exception.MemberNotFoundException;
import springexample.spring.model.Member;
import springexample.spring.service.MemberRegisterService;
import springexample.spring.service.RegisterRequest;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestMemberController {

    private MemberDao memberDao;
    private MemberRegisterService registerService;

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    public void setRegisterService(MemberRegisterService registerService) {
        this.registerService = registerService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new RegisterRequestValidator());
    }

    @GetMapping("/api/members")
    public List<Member> members() {
        return memberDao.selectAll();
    }

    @GetMapping("/api/members/{id}")
    public Member member(@PathVariable("id") Long id) {
        Member member = memberDao.selectById(id);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        return member;
    }

    @PostMapping("/api/members/new")
    public ResponseEntity<Object> newMember(@RequestBody @Valid RegisterRequest registerRequest, Errors errors) {
        if (errors.hasErrors()) {
            String errorCodes =
                    errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes:" + errorCodes));
        }
        Long newMemberId = registerService.regist(registerRequest);
        URI uri = URI.create("/api/members/" + newMemberId);
        return ResponseEntity.created(uri).build();
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No such member"));
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateMember() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Already exist"));
    }
}
