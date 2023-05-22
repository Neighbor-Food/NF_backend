package com.neighborfood.neighborfoodback.dto;

import com.neighborfood.neighborfoodback.entity.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDTO {
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class request {
        private String title;
        private String contents;
        private String category;
        private Integer latitude;
        private Integer longitude;
        private LocalDateTime order_time;
        private Integer max_people;

        private Integer restaurant_no;
    }

    @Builder
    @Getter
    @Setter
    public static class requestModify {
        private Integer board_no;
        private String title;
        private String contents;
        private String category;
        private Integer latitude;
        private Integer longitude;
        private LocalDateTime order_time;
        private Integer max_people;

        private Integer restaurant_no;
    }

    @Builder
    @Getter
    @Setter
    public static class info {
        private Integer board_no;
        private String title;
        private String contents;
        private String category;
        private Integer latitude;
        private Integer longitude;
        private LocalDateTime order_time;
        private Integer max_people;
        private Integer cur_people;

        private LocalDateTime reg_date;
        private LocalDateTime mod_date;

        private MemberDTO.info member;
        private Restaurant restaurant;
    }

    @Builder
    @Getter
    @Setter
    public static class detail {
        private Integer board_no;
        private String title;
        private String contents;
        private String category;
        private Integer latitude;
        private Integer longitude;
        private LocalDateTime order_time;
        private Integer max_people;
        private Integer cur_people;

        private LocalDateTime reg_date;
        private LocalDateTime mod_date;

        private List<ParticipationDTO.info> participant;
        private List<ReplyDTO.info> reply;
        private MemberDTO.info member;
        private Restaurant restaurant;
    }
}
