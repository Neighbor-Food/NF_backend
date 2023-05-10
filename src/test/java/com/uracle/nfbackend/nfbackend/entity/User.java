package com.uracle.nfbackend.nfbackend.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Todo : 테스트 완료 후 Member로 변경해야함
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private String id;
    private String name;
    private String email;

    // firebase timestamp type
    private Date create_dt;
    private Date update_dt;

}