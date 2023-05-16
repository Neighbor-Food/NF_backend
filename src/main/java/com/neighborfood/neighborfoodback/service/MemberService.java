package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.EmailAuth;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.repository.EmailAuthRepository;
import com.neighborfood.neighborfoodback.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EmailAuthRepository emailAuthRepository;

    @Autowired
    private EmailAuthService emailAuthService;

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

        // 코드인증회원가입방식
//        Optional<Member> existMember = memberRepository.findByEmail(member.getEmail());
//        if (existMember.isPresent()) {
//            Member realMember = existMember.get();
//            log.warn("real member password >>> {}", realMember.getPassword());
////            // 중복된 이메일 회원가입 처리: 이메일이 존재하고, 인증이 되었고, 비밀번호도 있음 true, true
////            if (memberRepository.existsByEmail(realMember.getEmail()) && realMember.getEmail_auth() && realMember.getPassword() != null) {
////                // catch exception
////                log.warn("email already exist {}", realMember.getEmail());
////                throw new RuntimeException("email already exist");
////            }
//             if (memberRepository.existsByEmail(realMember.getEmail()) && !realMember.getEmail_auth()) {
//                // 이메일 존재하고, 인증되지 않은 회원 true, false
//                log.warn("이메일 인증이 필요합니다.");
//                throw new RuntimeException("Authentication is required");
//            } else {
//                return memberRepository.save(member);
//            }
//        } else {
//            // 새 회원
//            return memberRepository.save(member);
//        }
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
            log.warn("invalid arguments");
            throw new RuntimeException("invalid arguments");
        }
        Member member = memberRepository.findByEmailAndPassword(email, password);
        // 이메일, 비번 입력했지만 회원가입 한 대상이 아닌 경우 처리
        if (member == null) {
            // catch exception
            log.warn("member does not exist");
            throw new RuntimeException("member does not exist");
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
            emailAuthRepository.delete(emailAuth);
            member.emailVerifiedSuccess(); // 인증 true 로 전환
            memberRepository.save(member); // db 에 상태 저장
        } else {
            throw new RuntimeException("token not found");
        }

        /*Optional<EmailAuth> findEmailAuth = emailAuthRepository.findByEmail(email);
        if (findEmailAuth.isPresent()) {
            EmailAuth emailAuth = findEmailAuth.get();
            // 인증 코드 비교 처리
            if (!emailAuth.getCode().equals(code)) {
                log.warn("email auth codes are not same");
                throw new RuntimeException("email auth code are not same");
            }

            Optional<Member> findMember = memberRepository.findByEmail(emailAuth.getEmail());
            // member verified 처리
            if (findMember.isPresent()) {
                Member member = findMember.get();
                emailAuth.useToken();    // 토큰 만료 로직을 구현해주면 된다. ex) expired 값을 true로 변경
                member.emailVerifiedSuccess();    // 유저의 이메일 인증 값 변경 로직을 구현해주면 된다. ex) emailVerified 값을 true로 변경
                log.warn("member email >>> {}", member.getEmail());
                log.warn("verified >>> {}", member.getEmail_auth());
                log.warn("usetoken >>> {}", emailAuth.isExpired());
                memberRepository.save(member);
                emailAuthRepository.save(emailAuth);
            } else {
                log.warn("member does not exist");
                throw new RuntimeException("member does not exist");
            }
        } else {
            log.warn("email auth token does not exist");
            throw new RuntimeException("email auth token does not exist");
        }*/
    }
}
