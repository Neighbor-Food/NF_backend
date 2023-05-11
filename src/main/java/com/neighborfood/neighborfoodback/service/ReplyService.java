package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.Reply;
import com.neighborfood.neighborfoodback.repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    // 댓글 조회
    public Reply getReply(Integer id){
        Optional<Reply> reply = replyRepository.findById(id);
        if (reply.isPresent()){
            return reply.get();
        } else {
            log.warn("reply does not exists");
            throw new RuntimeException("reply does not exists");
        }
    }

    // 댓글 생성
    public Reply create(Reply reply){
        return replyRepository.save(reply);
    }

    public void delete(Integer id){
        replyRepository.deleteById(id);
    }
}
