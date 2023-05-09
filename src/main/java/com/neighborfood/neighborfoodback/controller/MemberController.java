package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.MemberDTO;
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

import javax.validation.Valid;

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
    public ResponseEntity<?> join(@RequestBody MemberDTO memberDTO){
        try{
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
            MemberDTO responseDTO = MemberDTO.builder()
                    .email(registeredMember.getEmail())
                    .password(registeredMember.getPassword())
                    .name(registeredMember.getName())
                    .push_email(registeredMember.getPush_email())
                    .bank(registeredMember.getBank())
                    .bank_account_number(registeredMember.getBank_account_number())
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch(Exception e) {
            ResponseDTO<?> responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 로그인 (토큰 발행)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO){
        Member member = memberService.getByCredentials(memberDTO.getEmail(), memberDTO.getPassword());
        if (member != null) {
            String token = tokenProvider.create(member);
            MemberDTO responseDTO = MemberDTO.builder()
                    .member_no(member.getMember_no())
                    .email(member.getEmail())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } else {
            ResponseDTO<?> responseDTO = ResponseDTO.builder()
                    .error("login failed")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 회원탈퇴 (이메일 받아서 삭제)
    @GetMapping("/out")
    public ResponseEntity<?> out(@RequestBody MemberDTO memberDTO){
        MemberDTO responseDTO = MemberDTO.builder()
                .email(memberDTO.getEmail())
                .build();
        memberService.delete(memberDTO.getEmail());
        return ResponseEntity.ok().body(responseDTO);
    }

    // 회원정보 get (이메일, 이름, 은행, 계좌번호)
    @GetMapping("/getMember")
    public ResponseEntity<?> getMember(@AuthenticationPrincipal String email){
        Member member = memberService.getMember(email);
        MemberDTO responseDTO = MemberDTO.builder()
                .email(member.getEmail())
                .name(member.getName())
                .bank(member.getBank())
                .bank_account_number(member.getBank_account_number())
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }

    // 회원 정보 수정
    @PostMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody MemberModifyDTO memberModifyDTO, @AuthenticationPrincipal String email){
        if (!memberModifyDTO.getPassword1().equals(memberModifyDTO.getPassword2())){
            ResponseDTO<?> responseDTO = ResponseDTO.builder()
                    .error("2개의 패스워드 불일치")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }

        Member member = memberService.getMember(email);
        Member modifiedMember = memberService.modify(
                member,
                memberModifyDTO.getPassword1(),
                memberModifyDTO.getName(),
                memberModifyDTO.getPush_email(),
                memberModifyDTO.getBank(),
                memberModifyDTO.getBank_account_number()
        );
        // 응답
        MemberDTO responseDTO = MemberDTO.builder()
                .email(modifiedMember.getEmail())
                .password(modifiedMember.getPassword())
                .name(modifiedMember.getName())
                .push_email(modifiedMember.getPush_email())
                .bank(modifiedMember.getBank())
                .bank_account_number(modifiedMember.getBank_account_number())
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }
}