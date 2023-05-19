package com.neighborfood.neighborfoodback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighborfood.neighborfoodback.entity.Menu;
import com.neighborfood.neighborfoodback.repository.MenuRepository;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }

    public Optional<Menu> getById(Integer id) {
        return menuRepository.findById(id);
    }

    public List<Menu> getByRestaurantId(Integer id) {
        return menuRepository.findByRestaurantId(id);
    }

}
