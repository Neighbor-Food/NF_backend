package com.neighborfood.neighborfoodback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDTO {
    private String token;
    private Integer member_no;
    private String email;
    private String password;
    private String name;
    private String push_email;
    private String bank;
    private String bank_account_number;
}