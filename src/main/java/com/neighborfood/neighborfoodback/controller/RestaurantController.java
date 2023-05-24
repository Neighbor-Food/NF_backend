package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.dto.ResponseListDTO;
import com.neighborfood.neighborfoodback.dto.RestaurantDTO;
import com.neighborfood.neighborfoodback.entity.Restaurant;
import com.neighborfood.neighborfoodback.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    // 음식점 리스트 조회
    @GetMapping("/getList")
    public ResponseEntity<?> getList() {
        try {
            // 음식점 리스트 가져오기
            List<Restaurant> list = restaurantService.getList();
            // restaurant info dto list 로 변환
            List<RestaurantDTO.info> restaurantInfoDTOList = Restaurant.toInfoDTOList(list);
            // 응답
            ResponseListDTO<RestaurantDTO.info> responseDTO = ResponseListDTO.<RestaurantDTO.info>builder()
                    .result("success")
                    .data(restaurantInfoDTOList)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 특정 음식점 조회
    @GetMapping("/get/{restaurant_no}")
    public ResponseEntity<?> getRestaurant(@PathVariable("restaurant_no") Integer restaurant_no) {
        try {
            // 음식점 가져오기
            Restaurant restaurant = restaurantService.getRestaurant(restaurant_no);
            // restaurant info dto 로 변환
            RestaurantDTO.info restaurantInfoDTO = Restaurant.toInfoDTO(restaurant);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(restaurantInfoDTO)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 카테고리 별 음식점 리스트 조회
    @GetMapping("/getListByCategory")
    public ResponseEntity<?> getListByCategory(@RequestParam("category") String category) {
        try {
            // 카테고리에 대한 게시글 리스트 가져오기
            List<Restaurant> list = restaurantService.getListByCategory(category);
            // board info dto list 로 변환
            List<RestaurantDTO.info> restaurantInfoDTOList = Restaurant.toInfoDTOList(list);
            // 응답
            ResponseListDTO<RestaurantDTO.info> responseDTO = ResponseListDTO.<RestaurantDTO.info>builder()
                    .result("success")
                    .data(restaurantInfoDTOList)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

//    작동함. byte[] 리턴
//    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getImage(@RequestParam("filename") String filename) throws IOException {
//        String imagePath = "static/images/" + filename; // 이미지 파일의 경로와 파일명을 설정합니다.
//        ClassPathResource imageResource = new ClassPathResource(imagePath);
//
//        if (imageResource.exists()) {
//            InputStream imageStream = imageResource.getInputStream();
//            return StreamUtils.copyToByteArray(imageStream);
//        } else {
//            // 파일이 존재하지 않을 경우 예외 처리를 진행하거나 기본 이미지를 반환하는 등의 로직을 추가할 수 있습니다.
//            throw new RuntimeException("Image not found: " + filename);
//        }
//    }

//    작동함. ResponseEntity<Resource> 리턴
//    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<Resource> getImage(@RequestParam("filename") String filename) {
//        try {
//            // 파일 경로를 설정합니다. resources/static 폴더에 있는 이미지를 가져옵니다.
//            String filePath = "static/restaurantImages/" + filename;
//            Resource resource = new ClassPathResource(filePath);
//
//            // 이미지를 반환합니다.
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG)
//                    .body(resource);
//        } catch (Exception e) {
//            // 파일을 찾지 못한 경우 예외 처리합니다.
//            return ResponseEntity.notFound().build();
//        }
//    }

//    작동함. ResponseEntity<byte[]> 리턴
//    @GetMapping(value = "/image/{filename}")
//    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
//        try {
//            // 파일 경로를 설정합니다. resources/static 폴더에 있는 이미지를 가져옵니다.
//            String filePath = "static/" + filename;
//            String extension = determineExtension(filename); // 확장자를 추출합니다.
//
//            // 파일을 읽어 byte 배열로 변환합니다.
//            Resource resource = new ClassPathResource(filePath + extension);
//            byte[] imageBytes = Files.readAllBytes(Path.of(resource.getURI()));
//
//            // HttpHeaders를 설정합니다.
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_JPEG);
//
//            // byte 배열과 HttpHeaders를 사용하여 ResponseEntity를 생성하여 반환합니다.
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(imageBytes);
//        } catch (IOException e) {
//            // 파일을 찾지 못한 경우나 읽을 수 없는 경우 예외 처리합니다.
//            return ResponseEntity.notFound().build();
//        }
//    }

//    작동함. ReponseEntity<Resource> 리턴
    @GetMapping(value = "/getImage/{restaurant_name}")
    public ResponseEntity<Resource> getImage(@PathVariable("restaurant_name") String imageName) {
        try {
            // 파일 경로를 설정합니다. resources/static/restaurantImages 폴더에 있는 이미지 name 을 가져옴.
            String filePath = "static/restaurantImages/" + imageName;
            String extension = determineExtension(imageName); // 확장자 추가.

            // resources/static/restaurantImages 아래에 있는 파일을 읽음.
            Resource resource = new ClassPathResource(filePath + extension);

            // HttpHeaders를 설정.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            // byte 배열과 HttpHeaders를 사용하여 ResponseEntity를 생성하여 반환.
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            // 파일을 찾지 못한 경우나 읽을 수 없는 경우 예외 처리.
            return ResponseEntity.notFound().build();
        }
    }

    // 파일명에 확장자를 붙이는 유틸리티 메서드
    private String determineExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex != -1 && lastDotIndex < filename.length() - 1) {
            return filename.substring(lastDotIndex);
        }
        return ".jpg"; // 기본 확장자로 .jpg를 사용.
    }

}
