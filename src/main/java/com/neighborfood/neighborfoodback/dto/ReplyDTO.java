package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
        private String reg_date;
        private String mod_date;
        private String writer;
    }
}
