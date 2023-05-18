package com.neighborfood.neighborfoodback.dto;

import com.neighborfood.neighborfoodback.entity.Restaurant;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RestaurantDTO {
    private Integer restaurant_no;

    private String name;
    private String category;
    private String delivery_tip;
    private String min_order_price;
}
