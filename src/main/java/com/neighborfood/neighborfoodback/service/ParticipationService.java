package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.entity.Participation;
import com.neighborfood.neighborfoodback.entity.Reply;
import com.neighborfood.neighborfoodback.repository.ParticipationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ParticipationService {
    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private EmailAuthService emailAuthService;

    public Participation create(Participation participation) {
        if (participation == null) {
            // catch exception
            log.warn("participation is null");
            throw new RuntimeException("participation is null");
        }
        // 이미 참가중인 유저라면 exception
        if (participationRepository.existsByBoardAndMember(participation.getBoard(), participation.getMember())) {
            // catch exception
            log.warn("Member is already participating : {}", participation.getMember().getEmail());
            throw new RuntimeException("Member is already participating");
        }
        return participationRepository.save(participation);
    }

    public void delete(Board board, Member member) {
        Participation participation = findByBoardAndMember(board, member);
        participationRepository.delete(participation);
    }

    public Integer countByBoard(Board board) {
        return participationRepository.countByBoard(board);
    }

    public Participation findByBoardAndMember(Board board, Member member) {
        Optional<Participation> participation = participationRepository.findByBoardAndMember(board, member);
        if (participation.isPresent()) {
            return participation.get();
        } else {
            // catch exception
            log.warn("참여 정보가 없습니다.");
            throw new RuntimeException("참여 정보가 없습니다.");
        }
    }

    public List<Board> myParticipationBoardList(Member member) {
        List<Participation> participationList = participationRepository.findAllByMember(member);
        List<Board> myParticipationBoardList = new ArrayList<>();
        if (participationList.isEmpty()) {
            return myParticipationBoardList;
        }
        for (Participation part : participationList) {
            myParticipationBoardList.add(part.getBoard());
        }
        return myParticipationBoardList;
    }

    // 새 댓글 알림 (메일 전송)
    public void sendReplyMail(Member member, Board board, Reply reply) {
        // board 에 속한 참가자들 리스트 받아오기
        List<Participation> participationList = participationRepository.findAllByBoard(board);
        // 참가자들이 없으면 걍 return
        if (participationList.isEmpty()) {
            return;
        }
        // 참가자 리스트 반복문 시작
        for (Participation participant : participationList) {
            // 만약 참가자의 push email 이 존재하지 않는다면 처리
            if (participant.getMember().getPush_email().equals("")) {
                continue;
            }
            // 만약 참가자의 email 이 새 댓글 작성자의 email 과 동일하다면 처리
            if (participant.getMember().getEmail().equals(member.getEmail())) {
                continue;
            }
            // 이메일 인증 메일 send
            emailAuthService.createReplyMail(participant.getMember().getPush_email(), board, reply);
        }
    }
}
