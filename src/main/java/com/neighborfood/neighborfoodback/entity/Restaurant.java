package com.neighborfood.neighborfoodback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neighborfood.neighborfoodback.dto.BoardDTO;
import com.neighborfood.neighborfoodback.dto.RestaurantDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurant_no;

    private String name;
    private String category;
    private String delivery_tip;
    private String min_order_price;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Board> boardList;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menuList;

    public static RestaurantDTO.info toInfoDTO(Restaurant restaurant) {
        return RestaurantDTO.info.builder()
                .id(restaurant.getRestaurant_no())
                .name(restaurant.getName())
                .category(restaurant.getCategory())
                .delivery_tip(restaurant.getDelivery_tip())
                .min_order_price(restaurant.getMin_order_price())

                .build();
    }

    public static List<RestaurantDTO.info> toInfoDTOList(List<Restaurant> restaurantList) {
        return restaurantList.stream().map(Restaurant::toInfoDTO).collect(Collectors.toList());
    }
}
