package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class RestaurantDTO {
    @Builder
    @Getter
    @Setter
    public static class info {
        private Integer restaurant_no;
        private String name;
        private String category;
        private String delivery_tip;
        private String min_order_price;
    }
}
