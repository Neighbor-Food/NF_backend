package com.neighborfood.neighborfoodback.controller;


import com.neighborfood.neighborfoodback.dto.ReplyDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.entity.Reply;
import com.neighborfood.neighborfoodback.service.BoardService;
import com.neighborfood.neighborfoodback.service.MemberService;
import com.neighborfood.neighborfoodback.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    // 댓글 생성: contents와 board_no를 받아서 처리
    @PostMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal String email, @RequestBody ReplyDTO.request replyRequestDTO) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 이메일 인증이 완료된 사용자인지 체크
            memberService.isAuthMem(member);
            // 게시글 정보 get
            Board board = boardService.getBoard(replyRequestDTO.getBoard_no());
            // dto 토대로 댓글 entity 생성
            Reply reply = Reply.builder()
                    .contents(replyRequestDTO.getContents())
                    .reg_date(LocalDateTime.now())
                    .member(member)
                    .board(board)
                    .build();
            // create
            Reply registeredReply = replyService.create(reply);
            // reply info dto 로 변환
            ReplyDTO.info replyInfoDTO = Reply.toInfoDTO(registeredReply);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(replyInfoDTO)
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

    // 댓글 삭제: 삭제할 댓글의 no를 받아서 처리
    @GetMapping("/delete/{reply_no}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal String email, @PathVariable("reply_no") Integer reply_no) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 댓글 정보 get
            Reply reply = replyService.getReply(reply_no);
            // 작성자 다를 경우 처리
            replyService.compareWriter1AndWriter2(reply.getMember().getMember_no(), member.getMember_no());
            // 삭제 후 응답
            replyService.delete(reply_no);
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

    @PostMapping("/modify")
    public ResponseEntity<?> modify(@AuthenticationPrincipal String email,
                                    @RequestBody ReplyDTO.requestModify replyModifyDTO) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 댓글 정보 get. 여기다가 수정할 것
            Reply reply = replyService.getReply(replyModifyDTO.getReply_no());
            // 작성자 다를 경우 처리
            replyService.compareWriter1AndWriter2(reply.getMember().getMember_no(), member.getMember_no());
            // reply entity 수정
            reply.setContents(replyModifyDTO.getContents());
            reply.setMod_date(LocalDateTime.now());
            // 수정
            Reply modifiedReply = replyService.modify(reply);
            // reply info dto 로 변환
            ReplyDTO.info replyInfoDTO = Reply.toInfoDTO(modifiedReply);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(replyInfoDTO)
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
