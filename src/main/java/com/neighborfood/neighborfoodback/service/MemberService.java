package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.EmailAuth;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.repository.EmailAuthRepository;
import com.neighborfood.neighborfoodback.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Slf4j
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EmailAuthRepository emailAuthRepository;

    public Member create(Member member) {
        if (member == null || member.getEmail() == null) {
            // catch exception
            log.warn("invalid arguments");
            throw new RuntimeException("invalid arguments");
        }

        // 중복된 이메일 회원가입 처리
        if (memberRepository.existsByEmail(member.getEmail())) {
            // catch exception
            log.warn("email already exist {}", member.getEmail());
            throw new RuntimeException("email already exist");
        }

        return memberRepository.save(member);
    }

    public void delete(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            Member regMember = member.get();
            memberRepository.deleteById(regMember.getMember_no());
        } else {
            // catch exception
            log.warn("member does not exist");
            throw new RuntimeException("member does not exist");
        }
    }

    public Member getMember(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            return member.get();
        } else {
            // catch exception
            log.warn("member does not exist or Member is not logged in");
            throw new RuntimeException("member does not exist or Member is not logged in");
        }
    }

    public Member getMemberByNo(Integer member_no) {
        Optional<Member> member = memberRepository.findById(member_no);
        if (member.isPresent()) {
            return member.get();
        } else {
            // catch exception
            log.warn("member does not exist");
            throw new RuntimeException("member does not exist");
        }
    }

    public Member modify(Member member) {
        if (member == null) {
            // catch exception
            log.warn("invalid argument");
            throw new RuntimeException("invalid argument");
        }
        return memberRepository.save(member);
    }

    // 이메일과 비밀번호로 회원 찾기
    public Member getByCredentials(String email, String password) {
        if (email == null || password == null) {
            // catch exception
            log.warn("아이디 또는 비밀번호가 입력되지 않았습니다.");
            throw new RuntimeException("아이디 또는 비밀번호가 입력되지 않았습니다.");
        }
        // 아이디와 비밀번호로 회원 조회
        Member member = memberRepository.findByEmailAndPassword(email, password);
        // 회원 조회가 안 된 경우 처리
        if (member == null) {
            // 이메일로 조회 했을 때 db에 존재 함 -> 비밀번호가 틀려서 회원 조회가 안된 경우
            Optional<Member> memberFoundByEmail = memberRepository.findByEmail(email);
            if (memberFoundByEmail.isPresent()) {
                // catch exception
                log.warn("패스워드가 틀렸습니다.");
                throw new RuntimeException("패스워드가 틀렸습니다.");
            }
            // catch exception
            log.warn("회원을 찾을 수 없습니다.");
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }

        return member;
    }

    // 패스워드 비교 함수
    public void comparePass1AndPass2(String password1, String password2) {
        boolean isSame = password1.equals(password2);
        if (!isSame) {
            // catch exception
            log.warn("password1 and password2 are not same");
            throw new RuntimeException("password1 and password2 are not same");
        }
    }

    // 인증된 사용자인지 체크
    public void isAuthMem(Member member) {
        if (!member.getEmail_auth()) {
            // catch exception
            log.warn("Not an authenticated member");
            throw new RuntimeException("Not an authenticated member");
        }
    }

    // 이메일 인증
    public void confirmEmailAuth(String token) {
        // 만료되지 않은 토큰이 있다면 토큰 get
//        EmailAuth findEmailAuth = emailAuthService.findByIdAndExpirationDateAfterAndExpired(token);
        Optional<EmailAuth> findEmailAuth = emailAuthRepository.findById(token);
        if (findEmailAuth.isPresent()) {
            EmailAuth emailAuth = findEmailAuth.get();
            Member member = getMember(emailAuth.getEmail());
            // todo: 일단 토큰 만료시간 없애놨음. 그리고 인증완료되면 토큰 삭제
            /*emailAuth.useToken(); // 토큰 만료
            emailAuthRepository.save(emailAuth); // db 에 상태 저장*/
//            emailAuthRepository.delete(emailAuth); // 토큰 삭제
            emailAuthRepository.deleteByEmail(emailAuth.getEmail()); // 해당 email 로 저장되어 있는 모든 토큰 삭제
            member.emailVerifiedSuccess(); // 인증 true 로 전환
            memberRepository.save(member); // db 에 상태 저장
        } else {
            throw new RuntimeException("token not found");
        }
    }
}
