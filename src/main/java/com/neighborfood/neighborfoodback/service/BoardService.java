package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
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
    public List<Board> getList() {
        List<Board> boardList = boardRepository.findAll();
        if (boardList.isEmpty()) {
            // catch exception
            log.warn("boards do not exist");
            throw new RuntimeException("boards do not exist");
        }
        return boardList;
    }

    // 게시글 조회
    public Board getBoard(Integer id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            return board.get();
        } else {
            // catch exception
            log.warn("board does not exist");
            throw new RuntimeException("board does not exist");
        }
    }

    public List<Board> getMyBoardList(Member member){
        List<Board> myBoardList = boardRepository.findAllByMember(member);
        if (myBoardList.isEmpty()){
            // catch exception
            log.warn("my board list does not exist");
            throw new RuntimeException("my board list does not exist");
        }
        return myBoardList;
    }

    // 게시글 생성
    public Board create(Board board) {
        if (board == null) {
            // catch exception
            log.warn("board does not exist");
            throw new RuntimeException("board does not exist");
        }
        return boardRepository.save(board);
    }

    public void delete(Integer id) {
        boardRepository.deleteById(id);
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
