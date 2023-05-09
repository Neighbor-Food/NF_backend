package com.neighborfood.neighborfoodback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberModifyDTO {
    private String password1;

    private String password2;

    private String name;
    private String push_email;
    private String bank;
    private String bank_account_number;
}
