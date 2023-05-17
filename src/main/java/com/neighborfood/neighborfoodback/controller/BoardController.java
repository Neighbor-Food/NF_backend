package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.BoardDTO;
import com.neighborfood.neighborfoodback.dto.BoardModifyDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.dto.ResponseListDTO;
import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.entity.Restaurant;
import com.neighborfood.neighborfoodback.service.BoardService;
import com.neighborfood.neighborfoodback.service.MemberService;
import com.neighborfood.neighborfoodback.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Autowired
    private RestaurantService restaurantService;

    // 게시글 리스트 조회
    @GetMapping("/getList")
    public ResponseEntity<?> getList() {
        try {
            List<Board> list = boardService.getList();
            ResponseListDTO<Board> responseDTO = ResponseListDTO.<Board>builder()
                    .result("success")
                    .data(list)
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

    // 특정 게시글 조회: 조회할 게시글의 id 값을 받아서 처리
    @GetMapping("/get/{board_no}")
    public ResponseEntity<?> getBoard(@PathVariable("board_no") Integer board_no) {
        try {
            Board board = boardService.getBoard(board_no);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(board)
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

    // 게시글 생성
    @PostMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal String email, @RequestBody BoardDTO boardDTO) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 이메일 인증이 완료된 사용자인지 체크
            memberService.isAuthMem(member);
            // dto 토대로 board entity 생성
            Restaurant restaurant = restaurantService.getRestaurant(boardDTO.getRestaurant_no());
            Board board = Board.builder()
                    .title(boardDTO.getTitle())
                    .contents(boardDTO.getContents())
                    .category(boardDTO.getCategory())
                    .latitude(boardDTO.getLatitude())
                    .longitude(boardDTO.getLongitude())
                    .order_time(boardDTO.getOrder_time())
                    .max_people(boardDTO.getMax_people())

                    .reg_date(LocalDateTime.now())
                    .member(member)
                    .restaurant(restaurant)

                    .build();
            Board registeredBoard = boardService.create(board);
            // 만들어진 게시글 응답 (확인용)
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(registeredBoard)
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

    // 게시글 삭제: 삭제할 게시글의 id 값을 받아서 처리
    @GetMapping("/delete/{board_no}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal String email, @PathVariable("board_no") Integer board_no) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 게시글 정보 get
            Board board = boardService.getBoard(board_no);
            // 작성자 다를 경우 처리 (게시글의 작성자와 로그인한 사용자 no 값 비교)
            boardService.compareWriter1AndWriter2(board.getMember().getMember_no(), member.getMember_no());
            // 삭제 후 응답
            boardService.delete(board_no);
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

    // 게시글 수정: board_no부터 전체 데이터 받아서 처리
    @PostMapping("/modify")
    public ResponseEntity<?> modify(@AuthenticationPrincipal String email, @RequestBody BoardModifyDTO boardModifyDTO) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 게시글 정보 get
            Board board = boardService.getBoard(boardModifyDTO.getBoard_no());
            // 작성자 다를 경우 처리 (게시글의 작성자와 로그인한 사용자 no 값 비교)
            boardService.compareWriter1AndWriter2(board.getMember().getMember_no(), member.getMember_no());
            // dto 토대로 board entity 수정
            Restaurant restaurant = restaurantService.getRestaurant(boardModifyDTO.getRestaurant_no());
            board.setTitle(boardModifyDTO.getTitle());
            board.setContents(boardModifyDTO.getContents());
            board.setCategory(boardModifyDTO.getCategory());
            board.setLatitude(boardModifyDTO.getLatitude());
            board.setLongitude(boardModifyDTO.getLongitude());
            board.setOrder_time(boardModifyDTO.getOrder_time());
            board.setMax_people(boardModifyDTO.getMax_people());

            board.setMod_date(LocalDateTime.now());
            board.setRestaurant(restaurant);

            Board modifiedBoard = boardService.modify(board);
            // 만들어진 게시글 응답 (확인용)
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(modifiedBoard)
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

    @GetMapping("/myBoardList")
    public ResponseEntity<?> myBoardList(@AuthenticationPrincipal String email) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 내가 작성한 게시글 목록 가져오기
            List<Board> myBoardList = boardService.getMyBoardList(member);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(myBoardList)
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
