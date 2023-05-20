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
        private Integer boardNo;
        private Integer menuNo;
    }

    @Builder
    @Getter
    @Setter
    public static class requestModify{
        private Integer basketNo;
        private Integer quantity;
        private Integer menuNo;
    }
    
    @Builder
    @Getter
    @Setter
    public static class info{
        private Integer basketNo;
        private MemberDTO.info member;
        private Integer quantity;
        private Board board;
        private Menu menu;
    }

    
    @Builder
    @Getter
    @Setter
    public static class contr{
        private Integer basketNo;
        private String memberEmail;
        private Integer quantity;
        private Integer boardNo;
        private String menuName;
    }
}
