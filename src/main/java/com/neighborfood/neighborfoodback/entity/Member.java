package com.neighborfood.neighborfoodback.entity;

import lombok.*;

import javax.persistence.*;

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

}