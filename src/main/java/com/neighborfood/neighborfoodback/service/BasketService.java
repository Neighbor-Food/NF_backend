package com.neighborfood.neighborfoodback.service;

import java.util.List;
import java.util.Optional;

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

    public Basket getBasket(Integer basketNo) {
        Optional<Basket> basket = basketRepository.findById(basketNo);
        if (basket.isPresent()) {
            return basket.get();
        } else {
            // catch exception
            log.warn("basket does not exist");
            throw new RuntimeException("basket does not exist");
        }
    }

    public void compareWriter1AndWriter2(Integer writer1, Integer writer2) {
        boolean isSame = writer1.equals(writer2);
        if (!isSame) {
            // catch exception
            log.warn("writer1 and writer2 are not same");
            throw new RuntimeException("writer1 and writer2 are not same");
        }
    }

    public Basket modify(Basket basket) {
        if (basket == null) {
            // catch exception
            log.warn("invalid argument");
            throw new RuntimeException("invalid argument");
        }

        return basketRepository.save(basket);
    }
    
}
