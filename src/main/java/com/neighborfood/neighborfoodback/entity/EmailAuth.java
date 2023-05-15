package com.neighborfood.neighborfoodback.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class EmailAuth {

    // 토큰 만료 시간
    private static final Long MAX_EXPIRE_TIME = 5L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    private String code; // 인증코드

    private LocalDateTime expirationDate; // 만료날짜

    private boolean expired; // 만료여부

    // 사용자 email
    private String email;

    // 이메일 인증토큰 생성
    public static EmailAuth createEmailAuthToken(String email) {
        EmailAuth authToken = new EmailAuth();
        authToken.expirationDate = LocalDateTime.now().plusMinutes(MAX_EXPIRE_TIME); // 5분후 만료
        authToken.expired = false;
        authToken.email = email;

        // 인증코드 생성
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a~z (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        authToken.code = key.toString();

        return authToken;
    }

    // 토큰 사용으로 인한 만료
    public void useToken() {
        this.expired = true;
    }
}