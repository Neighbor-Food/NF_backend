package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.MemberDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.security.jwtTokenProvider;
import com.neighborfood.neighborfoodback.service.EmailAuthService;
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
    private EmailAuthService emailAuthService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberDTO.request memberRequestDTO) {
        try {
            // dto 토대로 member entity 생성
            Member member = Member.builder()
                    .email(memberRequestDTO.getEmail())
                    .password(memberRequestDTO.getPassword())
                    .name(memberRequestDTO.getName())
                    .push_email(memberRequestDTO.getPush_email())
                    .bank(memberRequestDTO.getBank())
                    .bank_account_number(memberRequestDTO.getBank_account_number())
                    .email_auth(false)
                    .build();
            // create
            Member registeredMember = memberService.create(member);
            // dto 로 response 시작
            // create 된 member entity 를 member info dto 로 변환
            MemberDTO.info memberInfoDTO = Member.toInfoDTO(registeredMember);
            // 이메일 인증 메일 send
            emailAuthService.createEmailAuthToken(registeredMember.getEmail());
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(memberInfoDTO)
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

    // 로그인 (토큰 발행)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDTO.login memberLoginDTO) {
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

    // 회원탈퇴 (로그인 되어있는 상태)
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
            // 현재 로그인 한 사용자 정보 get
            Member member = memberService.getMember(email);
            // member info dto 로 변환
            MemberDTO.info memberInfoDTO = Member.toInfoDTO(member);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(memberInfoDTO)
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
    public ResponseEntity<?> modify(@AuthenticationPrincipal String email, @RequestBody MemberDTO.requestModify memberModifyDTO) {
        try {
            // 현재 로그인 한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 기존 비밀번호, 입력받은 기존 비밀번호 비교 (다를 경우 exception)
            memberService.comparePass1AndPass2(member.getPassword(), memberModifyDTO.getCur_password());
            // member entity 수정
            member.setPassword(memberModifyDTO.getNew_password());
            member.setName(memberModifyDTO.getName());
            member.setPush_email(memberModifyDTO.getPush_email());
            member.setBank(memberModifyDTO.getBank());
            member.setBank_account_number(memberModifyDTO.getBank_account_number());
            // modify
            Member modifiedMember = memberService.modify(member);
            // member info dto 로 변환
            MemberDTO.info memberInfoDTO = Member.toInfoDTO(modifiedMember);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(memberInfoDTO)
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

    // 인증된 회원인지 get
    @GetMapping("isAuthMem")
    public ResponseEntity<?> isAuthMem(@AuthenticationPrincipal String email) {
        try {
            Member member = memberService.getMember(email);
            // 인증된 사용자인지 체크. 아니라면 exception
            memberService.isAuthMem(member);
            // member info dto 로 변환
            MemberDTO.info memberInfoDTO = Member.toInfoDTO(member);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(memberInfoDTO)
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

    // 현재 로그인 한 이메일로 인증 메일 보내기
    @GetMapping("sendEmailAuth")
    public ResponseEntity<?> sendEmailAuth(@AuthenticationPrincipal String email) {
        // 이메일 인증 메일 send
        emailAuthService.createEmailAuthToken(email);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .result("success")
                .data("인증 메일을 보냈습니다.")
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }

    // 이메일 인증 링크 접속
    @GetMapping("confirmEmailAuth")
    public ResponseEntity<?> confirmEmailAuth(@RequestParam String token) {
        try {
            memberService.confirmEmailAuth(token);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data("인증이 완료되었습니다")
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