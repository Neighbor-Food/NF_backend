package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.Member;
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
    public Reply getReply(Integer reply_no) {
        Optional<Reply> reply = replyRepository.findById(reply_no);
        if (reply.isPresent()) {
            return reply.get();
        } else {
            log.warn("reply does not exist");
            throw new RuntimeException("reply does not exist");
        }
    }

    // 댓글 생성
    public Reply create(Reply reply) {
        if (reply == null) {
            // catch exception
            log.warn("reply does not exist");
            throw new RuntimeException("reply does not exist");
        }
        return replyRepository.save(reply);
    }

    public void delete(Integer id) {
        replyRepository.deleteById(id);
    }

    public Reply modify(Reply reply) {
        if (reply == null) {
            // catch exception
            log.warn("invalid argument");
            throw new RuntimeException("invalid argument");
        }
        return replyRepository.save(reply);
    }

    public void compareWriter1AndWriter2(Integer writer1, Integer writer2) {
        boolean isSame = writer1.equals(writer2);
        if (!isSame) {
            // catch exception
            log.warn("writer1 and writer2 are not same");
            throw new RuntimeException("writer1 and writer2 are not same");
        }
    }
}
