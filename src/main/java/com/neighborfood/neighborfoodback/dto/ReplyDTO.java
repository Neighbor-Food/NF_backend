package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ReplyDTO {
    @Builder
    @Getter
    @Setter
    public static class request {
        private String contents;

        private Integer board_no;
    }

    @Builder
    @Getter
    @Setter
    public static class requestModify {
        private Integer reply_no;
        private String contents;
    }

    @Builder
    @Getter
    @Setter
    public static class info {
        private Integer reply_no;
        private String contents;
        private LocalDateTime reg_date;
        private LocalDateTime mod_date;
        private String writer;
    }
}
