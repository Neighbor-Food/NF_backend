package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseDTO<E> {
    private String error;
    private List<E> data;
}
