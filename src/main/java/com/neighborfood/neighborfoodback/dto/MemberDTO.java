package com.neighborfood.neighborfoodback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MemberDTO {
    @Getter
    @Setter
    @Builder
    public static class request {
        private String email;
        private String password;
        private String name;
        private String push_email;
        private String bank;
        private String bank_account_number;
    }

    @Getter
    @Setter
    public static class requestModify {
        private String cur_password;
        private String new_password;

        private String name;
        private String push_email;
        private String bank;
        private String bank_account_number;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class responseModify {
        private String new_password;
        private String name;
        private String push_email;
        private String bank;
        private String bank_account_number;
    }

    @Getter
    @Setter
    @Builder
    public static class login {
        private String email;
        private String password;
        private String token;
    }

    @Getter
    @Setter
    @Builder
    public static class info {
        private Integer member_no;
        private String email;
        private String name;
        private String push_email;
        private String bank;
        private String bank_account_number;
        private Boolean email_auth;
    }

}