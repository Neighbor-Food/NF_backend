package com.neighborfood.neighborfoodback.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer basket_no;
    private Integer menu_quantity;

    @JsonIgnore
    @JoinColumn(nullable = false, name = "board_no")
    private Board board;

    @JsonIgnore
    @JoinColumn(nullable = false, name = "member_no")
    private Member writer;

    @JsonIgnore
    @JoinColumn(nullable = false, name = "menu_no")
    private Menu menu;

}
