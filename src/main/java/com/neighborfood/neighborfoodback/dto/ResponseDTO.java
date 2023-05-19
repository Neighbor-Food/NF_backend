package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    private String result;
    private String error;
    private Object data;
}
