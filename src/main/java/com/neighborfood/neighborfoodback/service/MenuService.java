package com.neighborfood.neighborfoodback.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighborfood.neighborfoodback.entity.Menu;
import com.neighborfood.neighborfoodback.repository.MenuRepostory;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MenuService {
    @Autowired
    private MenuRepostory menuRepostory;

    public List<Menu> getList() {
        List<Menu> menuList = menuRepostory.findAllByRestaurant_no(null);
        if(menuList.isEmpty()){
            log.warn("menu list is empty");
            throw new RuntimeException("menu list is empty");
        }
        else{
            return menuList;
        }
    }
    
}
