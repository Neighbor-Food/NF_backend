package com.neighborfood.neighborfoodback.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

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
    private String price;
    private LocalDateTime last_update;

    private Integer restaurant_no;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.REMOVE)
    private List<Basket> basketList;
}
