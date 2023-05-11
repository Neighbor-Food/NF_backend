package com.neighborfood.neighborfoodback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reply_no;

    private String contents;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;


    @JsonIgnore
    @JoinColumn(name = "member_no")
    @ManyToOne
    private Member member;

    @JsonIgnore
    @JoinColumn(name = "board_no")
    @ManyToOne
    private Board board;
}
