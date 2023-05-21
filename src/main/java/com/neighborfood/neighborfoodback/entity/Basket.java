package com.neighborfood.neighborfoodback.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.engine.jdbc.batch.spi.BatchKey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neighborfood.neighborfoodback.dto.BasketDTO;
import com.neighborfood.neighborfoodback.dto.BasketDTO.contr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer basket_no;

    private Integer quantity;
    private boolean confirmed;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "board_no")
    private Board board;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_no")
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "menu_no")
    private Menu menu;

    public static BasketDTO.info toInfoDTO(Basket basket){
        return BasketDTO.info.builder()
                .basketNo(basket.getBoard().getBoard_no())
                .member(Member.toInfoDTO(basket.getMember()))
                .quantity(basket.getQuantity())
                .board(basket.getBoard())
                .menu(basket.getMenu())
                .confirmed(basket.isConfirmed())
                .build();

    }
    public static BasketDTO.contr toContrDTO(Basket basket){
        return BasketDTO.contr.builder()
                .basketNo(basket.getBasket_no())
                .memberEmail(basket.getMember().getEmail())
                .quantity(basket.getQuantity())
                .boardNo(basket.getBoard().getBoard_no())
                .menuName(basket.getMenu().getName())
                .confirmed(basket.isConfirmed())
                .build();
    }

    public static List<BasketDTO.contr> toContrDTOList(List<Basket> basketList) {
        return basketList.stream().map(Basket::toContrDTO).collect(Collectors.toList());
    }

}