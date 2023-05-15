package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ConfirmAuthDTO {
    private String email;
    private String code;
}
