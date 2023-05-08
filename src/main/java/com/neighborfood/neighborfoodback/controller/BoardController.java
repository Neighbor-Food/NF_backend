package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.BoardDTO;
import com.neighborfood.neighborfoodback.dto.MemberDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/get")
    public void  get(@AuthenticationPrincipal String email){
        System.out.println("boardcontroller >>> "+ email);
    }

    @PostMapping("/create")
    public ResponseEntity<?> post(@AuthenticationPrincipal String email, @RequestBody BoardDTO boardDTO){
        try{
/*            Board board = Board.builder()
                    .title(boardDTO.getTitle())
                    .contents(boardDTO.getContents())
                    .build();*/
            Board registeredBoard = boardService.create(
                    boardDTO.getTitle(),
                    boardDTO.getContents(),
                    email);
            // 만들어진 게시글 응답
            BoardDTO responseDTO = BoardDTO.builder()
                    .title(registeredBoard.getTitle())
                    .contents(registeredBoard.getContents())
                    .email(registeredBoard.getEmail())
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch(Exception e) {
            ResponseDTO<?> responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
