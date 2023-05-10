package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.dto.BoardDTO;
import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    // 게시글 리스트 조회
    public List<Board> getList(){
        return boardRepository.findAll();
    }

    // 게시글 조회
    public Board getBoard(Integer id){
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()){
            return board.get();
        } else {
            log.warn("board does not exists");
            throw new RuntimeException("board does not exists");
        }
    }

    // 게시글 생성
    public Board create(Board board){
        board.setReg_date(LocalDateTime.now());
        return boardRepository.save(board);
    }

    public void delete(Integer id){
        boardRepository.deleteById(id);
    }

}
