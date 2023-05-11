package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.MemberDTO;
import com.neighborfood.neighborfoodback.dto.MemberLoginDTO;
import com.neighborfood.neighborfoodback.dto.MemberModifyDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.security.jwtTokenProvider;
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

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberDTO memberDTO) {
        try {
            // dto 토대로 member entity 생성
            Member member = Member.builder()
                    .email(memberDTO.getEmail())
                    .password(memberDTO.getPassword())
                    .name(memberDTO.getName())
                    .push_email(memberDTO.getPush_email())
                    .bank(memberDTO.getBank())
                    .bank_account_number(memberDTO.getBank_account_number())
                    .build();
            Member registeredMember = memberService.create(member);
            // 만들어진 회원 정보 응답
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
        if (!memberModifyDTO.getPassword1().equals(memberModifyDTO.getPassword2())) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error("2개의 패스워드 불일치")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }

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
    }
}