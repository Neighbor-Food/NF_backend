package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Participation;
import com.neighborfood.neighborfoodback.repository.ParticipationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ParticipationService {
    @Autowired
    private ParticipationRepository participationRepository;

    public Participation create(Participation participation) {
        if (participation == null) {
            // catch exception
            log.warn("participation is null");
            throw new RuntimeException("participation is null");
        }
        // 이미 참가중인 유저라면 exception
        if (participationRepository.existsByMember(participation.getMember())) {
            // catch exception
            log.warn("Member is already participating : {}", participation.getMember().getEmail());
            throw new RuntimeException("Member is already participating");
        }
        return participationRepository.save(participation);
    }

    public Integer countByBoard(Board board) {
        if (board == null) {
            // catch exception
            log.warn("board is null");
            throw new RuntimeException("board is null");
        }
        return participationRepository.countByBoard(board);
    }

//    public List<Participation> getListByBoard(Board board) {
//        List<Participation> participationList = participationRepository.findAllByBoard(board);
//        if (participationList.isEmpty()) {
//            // catch exception
//            log.warn("participation list does not exist");
//            throw new RuntimeException("participation list does not exist");
//        }
//        return participationList;
//    }
}
