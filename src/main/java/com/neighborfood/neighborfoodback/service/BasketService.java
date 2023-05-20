package com.neighborfood.neighborfoodback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighborfood.neighborfoodback.entity.Basket;
import com.neighborfood.neighborfoodback.repository.BasketRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BasketService {
    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository){
        this.basketRepository = basketRepository;
    }

    //전체 조회
    public List<Basket> getList() {
        List<Basket> basketList = basketRepository.findAll();
        if (basketList.isEmpty()) {
            // catch exception
            log.warn("boards do not exist");
            throw new RuntimeException("boards do not exist");
        }
        return basketList;
    }

    //생성
    public Basket create(Basket basket) {
        if (basket == null) {
            // catch exception
            log.warn("board does not exist");
            throw new RuntimeException("board does not exist");
        }
        return basketRepository.save(basket);
    }
    
}
