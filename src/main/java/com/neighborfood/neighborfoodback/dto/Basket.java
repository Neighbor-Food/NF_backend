package com.neighborfood.neighborfoodback.dto;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.entity.Menu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Basket {
    private Integer menu_quantity;
    private Integer writer_no;
    private Integer board_no;
    private Integer menu_no;
}
