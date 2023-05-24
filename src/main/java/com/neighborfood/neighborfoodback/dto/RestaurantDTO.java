package com.neighborfood.neighborfoodback.dto;

import org.springframework.http.MediaType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class RestaurantDTO {
    @Builder
    @Getter
    @Setter
    public static class info {
        private Integer restaurant_no;

        private String name;
        private String category;
        private String delivery_tip;
        private String min_order_price;
        private String image_path; //우선 변경된 데이터가 제대로 작동하는지 테스트를 하기위해 String부터 출력해본다.
        // 작업 완료 후-> entity파일의 to~DTO(build)를 작성한다.
    }
    @Builder
    @Getter
    @Setter
    public static class detail {
        private Integer restaurant_no;

        private String name;
        private String category;
        private String delivery_tip;
        private String min_order_price;
        private MediaType image_file; //entity의 image_path에 대응하는 데이터, 이름이 다름에 유의한다.
    }
}
