package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.ParticipationDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.entity.Participation;
import com.neighborfood.neighborfoodback.service.BoardService;
import com.neighborfood.neighborfoodback.service.MemberService;
import com.neighborfood.neighborfoodback.service.ParticipationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/participation")
public class ParticipationController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ParticipationService participationService;

    @GetMapping("/in/{board_no}")
    public ResponseEntity<?> participateBoard(@AuthenticationPrincipal String email, @PathVariable("board_no") Integer board_no) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 게시글 정보 get
            Board board = boardService.getBoard(board_no);
            // 인원이 이미 다 찼다면 exception
            if (board.getMax_people() <= board.getCur_people()) {
                log.warn("인원을 더 이상 추가할 수 없습니다.");
                throw new RuntimeException("인원을 더 이상 추가할 수 없습니다.");
            }
            // 생성
            Participation participation = Participation.builder()
                    .board(board)
                    .member(member)
                    .build();
            // create
            Participation registeredParticipation = participationService.create(participation);
            ParticipationDTO.info participationInfoDTO = Participation.toInfoDTO(registeredParticipation);
            // set board cur_people and save status
            board.setCur_people(participationService.countByBoard(board));
            boardService.modify(board);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(participationInfoDTO)
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

    @GetMapping("out/{board_no}")
    public ResponseEntity<?> participationOutFromBoard(@AuthenticationPrincipal String email, @PathVariable("board_no") Integer board_no) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 게시글 정보 get
            Board board = boardService.getBoard(board_no);
            // delete
            participationService.delete(board, member);
            // set board cur_people and save status
            board.setCur_people(participationService.countByBoard(board));
            boardService.modify(board);
            // 응답
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
}
