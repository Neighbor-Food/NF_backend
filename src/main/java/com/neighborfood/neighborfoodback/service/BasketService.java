package com.neighborfood.neighborfoodback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighborfood.neighborfoodback.entity.Basket;
import com.neighborfood.neighborfoodback.entity.Member;
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

    public List<Basket> gellList() {
        return basketRepository.findAll();
    }

    public Optional<Basket> getById(Integer id) {
        return basketRepository.findById(id);
    }

    public Basket create(Basket basket) {
        if (basket == null) {
            // catch exception
            throw new RuntimeException("board does not exist");
        }
        return basketRepository.save(basket);
    }

    public List<Basket> getByBoardId(Integer boardId) {
        return basketRepository.findByBoardId(boardId);
    }

    public List<Basket> getByMemberMember_no(Integer member_no) {
        return basketRepository.findByMemberMember_no(member_no);
    }


}
