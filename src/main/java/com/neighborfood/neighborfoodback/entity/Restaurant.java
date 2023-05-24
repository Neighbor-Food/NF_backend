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
    //이미지 : entity로서 저장될 것을 가정해 String 타입으로 선언 한다
    private String image_path;
    //-> 작성후 DTO 작성

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Board> boardList;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menuList;

    public static RestaurantDTO.info toInfoDTO(Restaurant restaurant) {
        return RestaurantDTO.info.builder()
                .restaurant_no(restaurant.getRestaurant_no())
                .name(restaurant.getName())
                .category(restaurant.getCategory())
                .delivery_tip(restaurant.getDelivery_tip())
                .min_order_price(restaurant.getMin_order_price())
                .image_path(restaurant.getImage_path())
                .build();
    } //작업 완료 후 Controller Service, repository 확인, 이후 테스트, 그전에 먼저 데이터 채워넣기

    public static List<RestaurantDTO.info> toInfoDTOList(List<Restaurant> restaurantList) {
        return restaurantList.stream().map(Restaurant::toInfoDTO).collect(Collectors.toList());
    }
}
