package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class BoardDTO {
    private Integer board_no;

    private String title;
    private String contents;
    private String category;
    private String location;
    private Date order_time;
    private String max_people;
    private Date reg_date;
    private Date mod_date;

    private String email;
    private Integer restaurant_no;
}
