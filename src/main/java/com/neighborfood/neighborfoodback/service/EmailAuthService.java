package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.EmailAuth;
import com.neighborfood.neighborfoodback.entity.Reply;
import com.neighborfood.neighborfoodback.repository.EmailAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@EnableAsync
@RequiredArgsConstructor
public class EmailAuthService {
    /* 이메일 작성법
     * setTo 를 통해 누구에게 보낼지
     * setSubject 를 통해 제목을
     * setText 를 통해 내용을 작성할 수 있다.
     * */

    private final JavaMailSender javaMailSender;

    @Autowired
    private final EmailAuthRepository emailAuthRepository;


    // JavaMailSender 객체를 사용하여 Async 방식으로 이메일을 보낸다.
    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    // 이메일 인증 토큰 생성
    public void createEmailAuthToken(String email) {
        EmailAuth emailAuthToken = EmailAuth.createEmailAuthToken(email);
        emailAuthRepository.save(emailAuthToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        log.warn("auth service email >>> {}", email);
//        log.warn("auth service code >>> {}", emailAuthToken.getCode());
        mailMessage.setTo(email);
        mailMessage.setSubject("Neighborfood 인증 메일");
//        mailMessage.setText("인증번호: " + emailAuthToken.getCode());
        mailMessage.setText("http://localhost:8080/api/member/confirmEmailAuth?token=" + emailAuthToken.getId());
        sendEmail(mailMessage);

//        return emailAuthToken.getId();
    }

    public void createReplyMail(String push_email, Board board, Reply reply){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(push_email);
        mailMessage.setSubject("[Neighborfood]"+board.getTitle()+"에서 새 댓글이 작성되었습니다");
        mailMessage.setText(reply.getMember().getName()+": "+reply.getContents());
        sendEmail(mailMessage);
    }

    // 유효한 토큰 가져오기
    public EmailAuth findByIdAndExpirationDateAfterAndExpired(String authTokenId) {
        Optional<EmailAuth> authToken = emailAuthRepository.findByIdAndExpirationDateAfterAndExpired(authTokenId, LocalDateTime.now(), false);
        if (authToken.isPresent()) {
            return authToken.get();
        } else {
            throw new RuntimeException("token not found or expired token");
        }
    }
}