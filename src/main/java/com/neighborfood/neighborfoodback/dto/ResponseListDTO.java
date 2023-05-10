package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseListDTO<T> {
    private String result;
    private String error;
    private List<T> data;
}
