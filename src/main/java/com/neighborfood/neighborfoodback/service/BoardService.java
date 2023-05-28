package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    // 전체 게시글 리스트 조회
    public List<Board> getList() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    // 특정 게시글 조회
    public Board getBoard(Integer id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            return board.get();
        } else {
            // catch exception
            log.warn("존재하지 않는 게시글입니다.");
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }
    }

    // 내가 작성한 게시글 리스트 조회
    public List<Board> getMyBoardList(Member member) {
        List<Board> myBoardList = boardRepository.findAllByMember(member);
        return myBoardList;
    }

    // 카테고리에 대한 게시글 리스트 조회
    public List<Board> getListByCategory(String category) {
        List<Board> boardList = boardRepository.findByCategoryContaining(category);
        return boardList;
    }

    // 게시글 생성
    public Board create(Board board) {
        if (board == null) {
            // catch exception
            log.warn("게시글에 아무 내용이 없습니다.");
            throw new RuntimeException("게시글에 아무 내용이 없습니다.");
        }
        return boardRepository.save(board);
    }

    // 게시글 삭제
    public void delete(Integer id) {
        boardRepository.deleteById(id);
    }

    // 게시글 수정
    public Board modify(Board board) {
        if (board == null) {
            // catch exception
            log.warn("게시글에 아무 내용이 없습니다.");
            throw new RuntimeException("게시글에 아무 내용이 없습니다.");
        }

        return boardRepository.save(board);
    }

    // 작성자 비교 함수
    public void compareWriter1AndWriter2(Integer writer1, Integer writer2) {
        boolean isSame = writer1.equals(writer2);
        if (!isSame) {
            // catch exception
            log.warn("작성자가 다릅니다");
            throw new RuntimeException("작성자가 다릅니다.");
        }
    }
}
