package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.BoardDTO;
import com.neighborfood.neighborfoodback.dto.ParticipationDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.dto.ResponseListDTO;
import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.entity.Participation;
import com.neighborfood.neighborfoodback.entity.Restaurant;
import com.neighborfood.neighborfoodback.service.BoardService;
import com.neighborfood.neighborfoodback.service.MemberService;
import com.neighborfood.neighborfoodback.service.ParticipationService;
import com.neighborfood.neighborfoodback.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private ParticipationService participationService;

    // 게시글 리스트 조회
    @GetMapping("/getList")
    public ResponseEntity<?> getList() {
        try {
            // 게시글 리스트 가져오기
            List<Board> list = boardService.getList();
            // board info dto list 로 변환
            List<BoardDTO.info> boardInfoDTOList = Board.toInfoDTOList(list);
            // 응답
            ResponseListDTO<BoardDTO.info> responseDTO = ResponseListDTO.<BoardDTO.info>builder()
                    .result("success")
                    .data(boardInfoDTOList)
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
    public ResponseEntity<?> getBoard(@PathVariable("board_no") Integer board_no, @AuthenticationPrincipal String email) {
        try {
            // 해당 게시글 entity 가져오기
            Board board = boardService.getBoard(board_no);
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 게시글 참여 여부 알아보기. 미참여 -> exception
            Participation participation = participationService.findByBoardAndMember(board, member);
            // board detail dto 로 변환
            BoardDTO.detail boardDetailDTO = Board.toDetailDTO(board);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(boardDetailDTO)
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

    // 카테고리에 대한 게시글 리스트
    @GetMapping("/getListByCategory")
    public ResponseEntity<?> getListByCategory(@RequestParam("category") String category) {
        try {
            // 카테고리에 대한 게시글 리스트 가져오기
            List<Board> list = boardService.getListByCategory(category);
            // board info dto list 로 변환
            List<BoardDTO.info> boardInfoDTOList = Board.toInfoDTOList(list);
            // 응답
            ResponseListDTO<BoardDTO.info> responseDTO = ResponseListDTO.<BoardDTO.info>builder()
                    .result("success")
                    .data(boardInfoDTOList)
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
    public ResponseEntity<?> create(@AuthenticationPrincipal String email, @RequestBody BoardDTO.request boardRequestDTO) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 이메일 인증이 완료된 사용자인지 체크 아니라면 exception
            memberService.isAuthMem(member);
            // dto 토대로 board entity 생성
            Restaurant restaurant = restaurantService.getRestaurant(boardRequestDTO.getRestaurant_no());

            // 형식 변환 202301021730 -> 2023-01-02T17:30
            String strOrderTime = boardRequestDTO.getOrder_time();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            LocalDateTime strToLocalDateTime = LocalDateTime.parse(strOrderTime, format);

            Board board = Board.builder()
                    .title(boardRequestDTO.getTitle())
                    .contents(boardRequestDTO.getContents())
                    .category(boardRequestDTO.getCategory())
                    .latitude(boardRequestDTO.getLatitude())
                    .longitude(boardRequestDTO.getLongitude())
                    .location(boardRequestDTO.getLocation())
                    .order_time(strToLocalDateTime)
                    .max_people(boardRequestDTO.getMax_people())
                    .cur_people(1)

                    .reg_date(LocalDateTime.now())
                    .member(member)
                    .restaurant(restaurant)

                    .build();
            // create
            Board registeredBoard = boardService.create(board);
            // 방장도 참여시켜
            Participation participation = Participation.builder()
                    .board(registeredBoard)
                    .member(member)
                    .build();
            // create
            Participation registeredParticipation = participationService.create(participation);
            // board info dto 로 변환
            BoardDTO.info boardInfoDTO = Board.toInfoDTO(registeredBoard);
            // 만들어진 게시글 응답 (확인용)
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(boardInfoDTO)
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
    public ResponseEntity<?> modify(@AuthenticationPrincipal String email, @RequestBody BoardDTO.requestModify boardModifyDTO) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 게시글 정보 get. 여기다가 수정할 것
            Board board = boardService.getBoard(boardModifyDTO.getBoard_no());
            // 작성자 다를 경우 처리 (게시글의 작성자와 로그인한 사용자 no 값 비교)
            boardService.compareWriter1AndWriter2(board.getMember().getMember_no(), member.getMember_no());
            // dto 토대로 board entity 수정
            Restaurant restaurant = restaurantService.getRestaurant(boardModifyDTO.getRestaurant_no());

            // 형식 변환 202301021730 -> 2023-01-02T17:30
            String strOrderTime = boardModifyDTO.getOrder_time();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            LocalDateTime strToLocalDateTime = LocalDateTime.parse(strOrderTime, format);

            board.setTitle(boardModifyDTO.getTitle());
            board.setContents(boardModifyDTO.getContents());
            board.setCategory(boardModifyDTO.getCategory());
            board.setLatitude(boardModifyDTO.getLatitude());
            board.setLongitude(boardModifyDTO.getLongitude());
            board.setLocation(boardModifyDTO.getLocation());
            board.setOrder_time(strToLocalDateTime);
            board.setMax_people(boardModifyDTO.getMax_people());

            board.setMod_date(LocalDateTime.now());
            board.setRestaurant(restaurant);
            // modify
            Board modifiedBoard = boardService.modify(board);
            // board info dto 로 변환
            BoardDTO.info boardInfoDTO = Board.toInfoDTO(modifiedBoard);
            // 만들어진 게시글 응답 (확인용)
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(boardInfoDTO)
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
            // board info dto list 로 변환
            List<BoardDTO.info> boardInfoDTOList = Board.toInfoDTOList(myBoardList);
            // 응답
            ResponseListDTO<BoardDTO.info> responseDTO = ResponseListDTO.<BoardDTO.info>builder()
                    .result("success")
                    .data(boardInfoDTOList)
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

    // 내가 참여중인 게시글 리스트
    @GetMapping("/myParticipationBoardList")
    public ResponseEntity<?> myParticipationBoardList(@AuthenticationPrincipal String email) {
        try {
            // 현재 로그인한 사용자 정보 get
            Member member = memberService.getMember(email);
            // 내가 참여중인 게시글 리스트 가져오기
            List<Board> myParticipationBoardList = participationService.myParticipationBoardList(member);
            // board info dto list 로 변환
            List<BoardDTO.info> boardInfoDTOList = Board.toInfoDTOList(myParticipationBoardList);
            // 응답
            ResponseListDTO<BoardDTO.info> responseDTO = ResponseListDTO.<BoardDTO.info>builder()
                    .result("success")
                    .data(boardInfoDTOList)
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
