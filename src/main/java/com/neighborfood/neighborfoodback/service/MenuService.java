package com.neighborfood.neighborfoodback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighborfood.neighborfoodback.entity.Menu;
import com.neighborfood.neighborfoodback.entity.Restaurant;
import com.neighborfood.neighborfoodback.repository.MenuRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }

    public Menu getMenu(Integer menu_no) {
        Optional<Menu> board = menuRepository.findById(menu_no);
        if (board.isPresent()) {
            return board.get();
        } else {
            // catch exception
            log.warn("menu does not exist");
            throw new RuntimeException("board does not exist");
        }
    }

    public List<Menu> getMenuListByRestaurnatId(Restaurant restaurant) {
        List<Menu> restaurantMenuList = menuRepository.findAllByRestaurant(restaurant);

        if (restaurantMenuList.isEmpty()) {
            // catch exception
            log.warn("my board list does not exist");
            throw new RuntimeException("my board list does not exist");
        }
        return restaurantMenuList;
    }

}
