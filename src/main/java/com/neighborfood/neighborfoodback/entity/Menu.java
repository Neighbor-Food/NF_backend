package com.neighborfood.neighborfoodback.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.neighborfood.neighborfoodback.dto.MenuDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menu_no;
    private String name;
    private Integer price;
    private LocalDateTime last_update;

    @JsonBackReference
    @JoinColumn(name = "restaurant_no")
    @ManyToOne
    private Restaurant restaurant;

    public static MenuDTO.detail toDetailDTO(Menu menu) {
        return MenuDTO.detail.builder()
                .menu_no(menu.getMenu_no())
                .name(menu.getName())
                .price(menu.getPrice())
                .last_update(menu.getLast_update())
                .restaurant(menu.getRestaurant())
                .build();
    }

    public static MenuDTO.info toInfoDTO(Menu menu) {
        return MenuDTO.info.builder()
                .menu_no(menu.getMenu_no())
                .name(menu.getName())
                .price(menu.getPrice())
                .last_update(menu.getLast_update())
                .build();

    }
    public static List<MenuDTO.info> toInfoDTOList(List<Menu> menuList) {
        return menuList.stream().map(Menu::toInfoDTO).collect(Collectors.toList());
    }
}
