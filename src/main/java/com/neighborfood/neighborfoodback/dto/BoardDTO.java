package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BoardDTO {
    private Integer board_no;

    private String title;
    private String contents;

    private String email;
}
