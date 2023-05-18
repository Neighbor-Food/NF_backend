package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class BoardDTO {
    private String title;
    private String contents;
    private String category;
    private Integer latitude;
    private Integer longitude;
    private LocalDateTime order_time;
    private Integer max_people;
}
