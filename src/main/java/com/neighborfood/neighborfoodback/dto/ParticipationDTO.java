package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ParticipationDTO {
    @Builder
    @Getter
    @Setter
    public static class info {
        private Integer participation_no;

        private Integer board_no;
        private String participant_name;
    }
}
