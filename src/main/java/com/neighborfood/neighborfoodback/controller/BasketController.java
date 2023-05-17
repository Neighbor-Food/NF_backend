package com.neighborfood.neighborfoodback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neighborfood.neighborfoodback.service.BasketService;

@RequestMapping("api/basket")
public class BasketController {
    @Autowired
    private BasketService basketService;

    
}
