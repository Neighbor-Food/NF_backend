package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.BoardDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.dto.ResponseListDTO;
import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.service.BoardService;
import com.neighborfood.neighborfoodback.service.MemberService;
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
    @Autowired
    private MemberService memberService;

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

    // 특정 게시글 조회: 조회할 게시글의 id 값을 받아서 처리
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBoard(@PathVariable("id") Integer id){
        Board board = boardService.getBoard(id);
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
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            if (member == null){
                ResponseDTO responseDTO = ResponseDTO.builder()
                        .result("fail")
                        .error("비로그인 사용자입니다.")
                        .build();
                return ResponseEntity.badRequest().body(responseDTO);
            }
            // dto 토대로 board entity 생성
            Board board = Board.builder()
                    .title(boardDTO.getTitle())
                    .contents(boardDTO.getContents())
                    .category(boardDTO.getCategory())
                    .latitude(boardDTO.getLatitude())
                    .longitude(boardDTO.getLongitude())
                    .order_time(boardDTO.getOrder_time())
                    .max_people(boardDTO.getMax_people())
                    .member(member)
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

    // 게시글 삭제: 삭제할 게시글의 id 값을 받아서 처리
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal String email, @PathVariable("id") Integer id){
        Board board = boardService.getBoard(id);
        // 현재 로그인한 사용자 정보 get
        Member member = memberService.getMember(email);
        // 작성자 다를 경우 처리 (게시글의 작성자와 로그인한 사용자 no 값 비교)
        if (!board.getMember().getMember_no().equals(member.getMember_no())){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error("삭제 권한이 없습니다.")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
        boardService.delete(id);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .result("success")
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }
}
