package com.neighborfood.neighborfoodback.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuDTO {
    private Integer menu_no;
    private String name;
    private Integer price;
    private LocalDateTime last_update;
}
