package com.neighborfood.neighborfoodback.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer member_no;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private String push_email;
    private String bank;
    private String bank_account_number;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Board> boardList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE)
    private List<Basket> basketList;

    private Boolean email_auth;
    public void emailVerifiedSuccess() {
        this.email_auth = true;
    }
}