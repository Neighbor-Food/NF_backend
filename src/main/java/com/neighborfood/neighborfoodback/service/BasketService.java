package com.neighborfood.neighborfoodback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighborfood.neighborfoodback.entity.Basket;
import com.neighborfood.neighborfoodback.entity.Board;
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
    //조회
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
    //작성자 비교
    public void compareWriter1AndWriter2(Integer writer1, Integer writer2) {
        boolean isSame = writer1.equals(writer2);
        if (!isSame) {
            // catch exception
            log.warn("writer1 and writer2 are not same");
            throw new RuntimeException("writer1 and writer2 are not same");
        }
    }
    //수정
    public Basket modify(Basket basket) {
        if (basket == null) {
            // catch exception
            log.warn("invalid argument");
            throw new RuntimeException("invalid argument");
        }

        return basketRepository.save(basket);
    }
    //삭제
    public void delete(Integer id) {
        basketRepository.deleteById(id);
    }


    public List<Basket> getListByBoard(Board board) {
        List<Basket> basketList = basketRepository.findByBoard(board);
        if (basketList.isEmpty()) {
            // catch exception
            log.warn("basketList do not exist");
            throw new RuntimeException("basketList do not exist");
        }
        return basketList;
    }

    //null 반환
    public List<Basket> getListByBoardReturnNull(Board board) {
        List<Basket> basketList = basketRepository.findByBoard(board);
        if (basketList.isEmpty()) {
            // catch exception
            return null;
        }
        return basketList;
    }

    public List<Basket> getMyBoardList(Member member) {
        List<Basket> myBasketList = basketRepository.findAllByMember(member);
        if (myBasketList.isEmpty()) {
            // catch exception
            log.warn("my board list does not exist");
            throw new RuntimeException("my board list does not exist");
        }
        return myBasketList;
    }

    public Basket getMenu(Integer basket_no) {
        Optional<Basket> basket = basketRepository.findById(basket_no);
        if (basket.isPresent()) {
            return basket.get();
        } else {
            // catch exception
            log.warn("basket does not exist");
            throw new RuntimeException("basket does not exist");
        }
    }

    public void deleteList(List<Basket> basketList) {
        for(Basket basket : basketList){
            Integer basket_no = basket.getBasket_no();
            basketRepository.deleteById(basket_no);
        }
    }

    public List<Basket> getListByBoardAndMember(Board board, Member member) {
        //성능을 고려했을때 이후 수정 필요성 있음
        List<Basket> basketListByboard = basketRepository.findByBoard(board);
        List<Basket> basketListByMember = basketRepository.findAllByMember(member);

        List<Basket> basketList = basketListByboard.stream().filter(basketListByMember::contains).toList();

        return basketList;

    
    }
    
}
