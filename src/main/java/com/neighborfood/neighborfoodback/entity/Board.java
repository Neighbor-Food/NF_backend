package com.neighborfood.neighborfoodback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer board_no;

    private String title;
    private String contents;
    private String category;
    private Integer latitude;
    private Integer longitude;
    private LocalDateTime order_time;
    private Integer max_people;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;

    @JoinColumn(name = "member_no")
    @ManyToOne
    private Member member;

    @JoinColumn(name = "restaurant_no")
    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

}
