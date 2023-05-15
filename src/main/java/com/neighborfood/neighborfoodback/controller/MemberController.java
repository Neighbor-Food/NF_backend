package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.*;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.security.jwtTokenProvider;
import com.neighborfood.neighborfoodback.service.EmailSenderService;
import com.neighborfood.neighborfoodback.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private jwtTokenProvider tokenProvider;

    @Autowired
    private EmailSenderService emailSenderService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberDTO memberDTO) {
        try {
            // dto 토대로 member entity 생성
            Member member = memberService.getMember(memberDTO.getEmail());
            member.setPassword(memberDTO.getPassword());
            member.setName(memberDTO.getName());
            member.setPush_email(memberDTO.getPush_email());
            member.setBank(memberDTO.getBank());
            member.setBank_account_number(memberDTO.getBank_account_number());
            if (member.getEmail_auth()) {
                Member registeredMember = memberService.create(member);
                // 만들어진 회원 정보 응답
                ResponseDTO responseDTO = ResponseDTO.builder()
                        .result("success")
                        .data(registeredMember)
                        .build();
                return ResponseEntity.ok().body(responseDTO);
            } else {
                ResponseDTO responseDTO = ResponseDTO.builder()
                        .result("fail")
                        .data("Authentication is required")
                        .build();
                return ResponseEntity.badRequest().body(responseDTO);
            }
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 로그인 (토큰 발행)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginDTO memberLoginDTO) {
        try {
            // 넘어온 email, password 정보로 member 정보 get
            Member member = memberService.getByCredentials(memberLoginDTO.getEmail(), memberLoginDTO.getPassword());
            // token 생성
            String token = tokenProvider.create(member);
            memberLoginDTO.setToken(token);
            // 생성된 토큰 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(memberLoginDTO)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 회원탈퇴 (이메일 받아서 삭제)
    // todo: 추후 논의 필요 뭐 받아서 삭제할건지
    @GetMapping("/out")
    public ResponseEntity<?> out(@AuthenticationPrincipal String email) {
        try {
            memberService.delete(email);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 회원정보 get
    @GetMapping("/getMember")
    public ResponseEntity<?> getMember(@AuthenticationPrincipal String email) {
        try {
            Member member = memberService.getMember(email);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(member)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 회원 정보 수정
    @PostMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody MemberModifyDTO memberModifyDTO, @AuthenticationPrincipal String email) {
        try {
            // 비밀번호1 , 비밀번호2 비교 (다를 경우 exception)
            memberService.comparePass1AndPass2(memberModifyDTO.getPassword1(), memberModifyDTO.getPassword2());
            // 수정
            Member member = memberService.getMember(email);
            member.setPassword(memberModifyDTO.getPassword1());
            member.setName(memberModifyDTO.getName());
            member.setPush_email(memberModifyDTO.getPush_email());
            member.setBank(memberModifyDTO.getBank());
            member.setBank_account_number(memberModifyDTO.getBank_account_number());
            Member modifiedMember = memberService.modify(member);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(modifiedMember)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 이메일 인증 보내기 (이메일이 입력된 상태)
    @PostMapping("/sendEmailAuth")
    public ResponseEntity<?> sendEmailAuth(@RequestBody MemberDTO memberDTO) {
        try {
            // dto 토대로 member entity 생성 (현재 email 만 입력되어 인증 버튼 누른 상태)
            Member member = Member.builder()
                    .email(memberDTO.getEmail())
                    .email_auth(false)
                    .build();
            String code = emailSenderService.createEmailAuthToken(member.getEmail());
            log.warn("code >>> {}", code);
            Member registeredMember = memberService.create(member);
            // 이메일 인증 전송 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(registeredMember)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("confirmEmailAuth")
    public ResponseEntity<?> confirmEmailAuth(@RequestBody ConfirmAuthDTO confirmAuthDTO) {
        try {
            memberService.confirmEmailAuth(confirmAuthDTO.getEmail(), confirmAuthDTO.getCode());
            Member member = memberService.getMember(confirmAuthDTO.getEmail());
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(member)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}