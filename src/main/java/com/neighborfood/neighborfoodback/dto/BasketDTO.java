package com.neighborfood.neighborfoodback.dto;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.entity.Menu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class BasketDTO {
    @Builder
    @Getter
    @Setter
    public static class request{
        private Integer quantity;
        private Integer board_no;
        private Integer menu_no;
    }
    @Builder
    @Getter
    @Setter
    public static class requestModify{
        private Integer basket_no;
        private Integer quantity;
        private Integer menu_no;
    }
    
    public static class info{
        private Integer basket_no;
        private MemberDTO.info member;
        private Integer quantity;
        private Board board;
        private Menu menu;
    }
}
