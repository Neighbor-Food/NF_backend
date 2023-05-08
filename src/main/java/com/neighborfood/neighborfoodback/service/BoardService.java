package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board create(String title, String contents, String email){
        Board board = new Board();
        board.setTitle(title);
        board.setContents(contents);
        board.setEmail(email);
        return boardRepository.save(board);
    }
}
