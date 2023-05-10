package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.BoardDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.dto.ResponseListDTO;
import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 리스트 조회
    @GetMapping("/getList")
    public ResponseEntity<?> getList(){
        List<Board> list = boardService.getList();
        // TODO: 2023-05-10 dto list로 변환해서 보내야하나?
        ResponseListDTO<Board> responseDTO = ResponseListDTO.<Board>builder()
                .result("success")
                .data(list)
                .build();

        return ResponseEntity.ok().body(responseDTO);
    }

    // 특정 게시글 조회
    @GetMapping("/get/{no}")
    public ResponseEntity<?> getBoard(@PathVariable("no") Integer no){
        Board board = boardService.getBoard(no);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .result("success")
                .data(board)
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }

    // 게시글 생성
    @PostMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal String email, @RequestBody BoardDTO boardDTO){
        try{
            Board board = Board.builder()
                    .title(boardDTO.getTitle())
                    .contents(boardDTO.getContents())
                    .category(boardDTO.getCategory())
                    .location(boardDTO.getLocation())
                    .order_time(boardDTO.getOrder_time())
                    .max_people(boardDTO.getMax_people())
                    .email(email)
                    .restaurant_no(boardDTO.getRestaurant_no())
                    .build();
            Board registeredBoard = boardService.create(board);
            // 만들어진 게시글 응답 (확인용)
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(registeredBoard)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch(Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 게시글 삭제
    @GetMapping("/delete/{no}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal String email, @PathVariable("no") Integer no){
        Board board = boardService.getBoard(no);
        // 작성자 다를 경우 처리
        if (!board.getEmail().equals(email)){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error("삭제 권한이 없습니다.")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
        boardService.delete(no);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .result("success")
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }
}
