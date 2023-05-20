package com.neighborfood.neighborfoodback.dto;

import java.time.LocalDateTime;

import com.neighborfood.neighborfoodback.entity.Restaurant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MenuDTO {
    
    @Builder
    @Getter
    @Setter
    public static class detail{
        private Integer menu_no;
        private String name;
        private Integer price;
        private LocalDateTime last_update;
        
        private Restaurant restaurant;
    }

    @Builder
    @Getter
    @Setter
    public static class info {
        private Integer menu_no;
        private String name;
        private Integer price;
        private LocalDateTime last_update;
    }
}
