package com.neighborfood.neighborfoodback.dto;

import com.neighborfood.neighborfoodback.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ReplyDTO {
    private String contents;

    private Integer board_no;
}
