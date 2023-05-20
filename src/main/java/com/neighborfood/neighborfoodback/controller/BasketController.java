package com.neighborfood.neighborfoodback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neighborfood.neighborfoodback.dto.BasketDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.dto.ResponseListDTO;
import com.neighborfood.neighborfoodback.entity.Basket;
import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.entity.Menu;
import com.neighborfood.neighborfoodback.service.BasketService;
import com.neighborfood.neighborfoodback.service.BoardService;
import com.neighborfood.neighborfoodback.service.MemberService;
import com.neighborfood.neighborfoodback.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/basket")
public class BasketController {
    private final BasketService basketService;
    private final MemberService memberService;
    private final BoardService boardService;
    private final MenuService menuService;

    @Autowired
    public BasketController(BasketService basketService, MemberService memberService, BoardService boardService, MenuService menuService){
        this.basketService = basketService;
        this.memberService = memberService;
        this.boardService = boardService;
        this.menuService = menuService;
    }

//읽기
    //전체 읽기
    @GetMapping("/getList")
    public ResponseEntity<?> getList(){
        try{
            List<Basket> basketList = basketService.getList();
            List<BasketDTO.contr> basketContractionDTOList = Basket.toContrDTOList(basketList);
    
            ResponseListDTO<BasketDTO.contr> responseDTO = ResponseListDTO.<BasketDTO.contr>builder()
                    .result("success")
                    .data(basketContractionDTOList)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch(Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
    //멤버로 읽기
    //레스토랑으로 읽기
    //보드로 읽기
    //메뉴로 읽기

    //작성
    @PostMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal String email, @RequestBody BasketDTO.request basketRequestDTO){
        try{
            Member member = memberService.getMember(email);
            memberService.isAuthMem(member);
            Board board = boardService.getBoard(basketRequestDTO.getBoardNo());
            Menu menu = menuService.getMenu(basketRequestDTO.getMenuNo());

            Basket basket = Basket.builder()
                    .board(board)
                    .quantity(basketRequestDTO.getQuantity())
                    .member(member)
                    .menu(menu)
                    .build();
            Basket registeredBasket = basketService.create(basket);
            BasketDTO.contr basketContrDTO = Basket.toContrDTO(registeredBasket);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(basketContrDTO)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch(Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
    //수정
    @PutMapping("/modify")
    public ResponseEntity<?> modify(@AuthenticationPrincipal String email, @RequestBody BasketDTO.requestModify basketModifyDTO){
        try{
            Member member = memberService.getMember(email);
            Basket basket = basketService.getBasket(basketModifyDTO.getBasketNo());
            basketService.compareWriter1AndWriter2(basket.getMember().getMember_no(), member.getMember_no());

            Menu menu = menuService.getMenu(basketModifyDTO.getMenuNo());
            basket.setMenu(menu);
            basket.setQuantity(basketModifyDTO.getQuantity());

            Basket modifiedBasket = basketService.modify(basket);
            BasketDTO.contr basketContrDTO = Basket.toContrDTO(modifiedBasket);

            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(basketContrDTO)
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
    //삭제
    @DeleteMapping("/delete/{basket_no}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal String email, @PathVariable("basket_no") Integer basket_no){
        try{
            Member member = memberService.getMember(email);
            Basket basket = basketService.getBasket(basket_no);
            boardService.compareWriter1AndWriter2(basket.getMember().getMember_no(), member.getMember_no());

            basketService.delete(basket_no);

            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
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
}
