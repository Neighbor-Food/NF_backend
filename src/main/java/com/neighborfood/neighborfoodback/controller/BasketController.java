package com.neighborfood.neighborfoodback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neighborfood.neighborfoodback.repository.BasketRepository;
import com.neighborfood.neighborfoodback.service.BasketService;
import com.neighborfood.neighborfoodback.service.BoardService;
import com.neighborfood.neighborfoodback.service.MemberService;
import com.neighborfood.neighborfoodback.service.MenuService;
import com.neighborfood.neighborfoodback.dto.BasketDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.dto.ResponseListDTO;
import com.neighborfood.neighborfoodback.entity.Basket;
import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.entity.Menu;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/basek")
public class BasketController {
    //작성, 보드로 조회, 멤버로 조회, 전체 조회, 아이디 조회, 삭제
    private final BasketService basketService;
    private final BoardService boardService;
    private final MemberService memberService;
    private final MenuService menuService;

    @Autowired
    public BasketController(BasketService basketService, BoardService boardService, MemberService memberService, MenuService menuService){
        this.basketService = basketService;
        this.boardService = boardService;
        this.memberService = memberService;
        this.menuService = menuService;
    }

    //장바구니 전체 조회
    @GetMapping("/getList")
    public ResponseEntity<?> getList(){
        try{
            List<Basket> basketList = basketService.gellList();

            ResponseListDTO<Basket> responseDTO = ResponseListDTO.<Basket>builder()
                        .result("success")
                        .data(basketList)
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

    //id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getBasket(@PathVariable("id") Integer id){
        try{
            Optional<Basket> basket = basketService.getById(id);
            System.out.println(basket);
            ResponseDTO responseDTO = ResponseDTO.builder()
                        .result("success")
                        .data(basket)
                        .build();
            return ResponseEntity.ok().body(responseDTO);
        }catch(Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
    //board id로 조회
    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBasketByRestaurantId(@PathVariable("id") Integer id){
        try{
            List<Basket> basketList = basketService.getByBoardId(id);
            System.out.println(basketList);
            ResponseDTO responseDTO = ResponseDTO.builder()
                        .result("success")
                        .data(basketList)
                        .build();
            return ResponseEntity.ok().body(responseDTO);
        }catch(Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/member")
    public ResponseEntity<?> getBasketByRestaurantId(@AuthenticationPrincipal String email){
        try{
            Member member = memberService.getMember(email);
            List<Basket> basketList = basketService.getByMemberMember_no(member.getMember_no());
            ResponseDTO responseDTO = ResponseDTO.builder()
                        .result("success")
                        .data(basketList)
                        .build();
            return ResponseEntity.ok().body(responseDTO);
        }catch(Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    //장바구니 생성
    @PostMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal String email, @RequestBody BasketDTO basketDTO){
        try{
            System.out.println(email);
            Member member = memberService.getMember(email);
            Board board = boardService.getBoard(basketDTO.getBoardId());
            Menu menu = menuService.getById(basketDTO.getMenuId());

            Basket basket = Basket.builder()
                        .quantity(basketDTO.getQuantity())
                        .board(board)
                        .member(member)
                        .menu(menu)
                        .build();
            Basket registerBasket = basketService.create(basket);
            ResponseDTO responseDTO = ResponseDTO.builder()
                        .result("success")
                        .data(registerBasket)
                        .build();
            return ResponseEntity.ok().body(responseDTO);
        }
        catch(Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
