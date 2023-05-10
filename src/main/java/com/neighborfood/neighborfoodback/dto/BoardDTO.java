package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class BoardDTO {
    private Integer board_no;

    private String title;
    private String contents;
    private String category;
    private String location;
    private String order_time;
    private String max_people;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;

    private String email;
    private Integer restaurant_no;
}
