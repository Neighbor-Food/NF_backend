package com.neighborfood.neighborfoodback.entity;

import lombok.*;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Menu> menuList;

    public Integer getNo() {
        return restaurant_no;
    }
}
