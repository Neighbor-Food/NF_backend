package com.neighborfood.neighborfoodback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neighborfood.neighborfoodback.dto.MenuDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.entity.Menu;
import com.neighborfood.neighborfoodback.entity.Restaurant;
import com.neighborfood.neighborfoodback.service.MenuService;
import com.neighborfood.neighborfoodback.service.RestaurantService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;
    private final RestaurantService retaurantService;

    @Autowired
    public MenuController(MenuService menuService, RestaurantService retaurantService){
        this.menuService = menuService;
        this.retaurantService = retaurantService;
    }

    // 특정 메뉴 조회
    @GetMapping("/{menu_no}")
    public ResponseEntity<?> getMenu(@PathVariable("menu_no") Integer menu_no){
        try{
            System.out.println(menu_no);
            System.out.println(menuService.getMenu(menu_no));
            Menu menu = menuService.getMenu(menu_no);
            MenuDTO.detail menuDTO = Menu.toDetailDTO(menu);

            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(menuDTO)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch(Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    //식당 ID를 통한 메뉴 리스트 조회
    @GetMapping("/restaurant/{restaurant_no}")
    public ResponseEntity<?> getMEnuListByRestaurantId(@PathVariable("restaurant_no") Integer restaurant_no){
        try{
            Restaurant restaurant = retaurantService.getRestaurant(restaurant_no);
            List<Menu> menuList = menuService.getMenuListByRestaurnatId(restaurant);
            List<MenuDTO.info> menuInfoDTOList = Menu.toInfoDTOList(menuList);

            ResponseDTO responseDTO = ResponseDTO.builder()
            .result("success")
            .data(menuInfoDTOList)
            .build();
            return ResponseEntity.ok().body(responseDTO);
        }catch(Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/image/{menu_no}")
    public ResponseEntity<?> getMenuImage(@PathVariable("menu_no") Integer menu_no){
        try {

            // PathVariable 에 해당하는 primary key 로 해당 entity 를 찾음
            Menu menu = menuService.getMenu(menu_no);
            // 저장되어 있는 이미지 path 를 가져옴
            String image_path = menu.getImage_path();

            // resources/static/restaurantImages 아래에 있는 파일을 읽음.
            Resource resource = new ClassPathResource(image_path);

            // HttpHeaders를 설정.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            // ResponseEntity를 생성하여 반환.
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            // 파일을 찾지 못한 경우나 읽을 수 없는 경우 예외 처리.
            return ResponseEntity.notFound().build();
        }
    }
}
