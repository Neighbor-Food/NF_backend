package com.neighborfood.neighborfoodback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
}
