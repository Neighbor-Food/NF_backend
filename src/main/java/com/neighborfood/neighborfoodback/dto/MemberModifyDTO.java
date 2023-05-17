package com.neighborfood.neighborfoodback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberModifyDTO {
    private String cur_password;

    private String new_password;

    private String name;
    private String push_email;
    private String bank;
    private String bank_account_number;
}
