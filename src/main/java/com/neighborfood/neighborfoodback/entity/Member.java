package com.neighborfood.neighborfoodback.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.neighborfood.neighborfoodback.dto.MemberDTO;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Board> boardList;

    @JsonManagedReference
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    private Boolean email_auth;

    public void emailVerifiedSuccess() {
        this.email_auth = true;
    }

    public static MemberDTO.info toInfoDTO(Member member) {
        return MemberDTO.info.builder()
                .member_no(member.getMember_no())
                .name(member.getName())
                .push_email(member.getPush_email())
                .bank(member.getBank())
                .bank_account_number(member.getBank_account_number())
                .email_auth(member.getEmail_auth())
                .build();
    }

    public static MemberDTO.responseModify toResponseModifyDTO(Member member) {
        return MemberDTO.responseModify.builder()
                .new_password(member.getPassword())
                .name(member.getName())
                .push_email(member.getPush_email())
                .bank(member.getBank())
                .bank_account_number(member.getBank_account_number())
                .build();
    }
}