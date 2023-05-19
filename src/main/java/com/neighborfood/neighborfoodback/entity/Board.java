package com.neighborfood.neighborfoodback.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.neighborfood.neighborfoodback.dto.BoardDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer board_no;

    private String title;
    private String contents;
    private String category;
    private Integer latitude;
    private Integer longitude;
    private LocalDateTime order_time;
    private Integer max_people;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;

    @JsonBackReference
    @JoinColumn(name = "member_no")
    @ManyToOne
    private Member member;

    @JsonBackReference
    @JoinColumn(name = "restaurant_no")
    @ManyToOne
    private Restaurant restaurant;

    @JsonManagedReference
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    public static BoardDTO.info toInfoDTO(Board board) {
        return BoardDTO.info.builder()
                .board_no(board.getBoard_no())
                .title(board.getTitle())
                .contents(board.getContents())
                .category(board.getCategory())
                .latitude(board.getLatitude())
                .longitude(board.getLongitude())
                .order_time(board.getOrder_time())
                .max_people(board.getMax_people())

                .reg_date(board.getReg_date())
                .mod_date(board.getMod_date())

                .member(Member.toInfoDTO(board.getMember()))
                .restaurant(board.getRestaurant())

                .build();
    }

    public static List<BoardDTO.info> toInfoDTOList(List<Board> boardList) {
        return boardList.stream().map(Board::toInfoDTO).collect(Collectors.toList());
    }

    public static BoardDTO.detail toDetailDTO(Board board) {
        if (board.getReplyList() == null) {
            return BoardDTO.detail.builder()
                    .board_no(board.getBoard_no())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .category(board.getCategory())
                    .latitude(board.getLatitude())
                    .longitude(board.getLongitude())
                    .order_time(board.getOrder_time())
                    .max_people(board.getMax_people())

                    .reg_date(board.getReg_date())
                    .mod_date(board.getMod_date())

                    .member(Member.toInfoDTO(board.getMember()))
                    .restaurant(board.getRestaurant())
                    .build();
        } else {
            return BoardDTO.detail.builder()
                    .board_no(board.getBoard_no())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .category(board.getCategory())
                    .latitude(board.getLatitude())
                    .longitude(board.getLongitude())
                    .order_time(board.getOrder_time())
                    .max_people(board.getMax_people())

                    .reg_date(board.getReg_date())
                    .mod_date(board.getMod_date())

                    .reply(Reply.toInfoDTOList(board.getReplyList()))
                    .member(Member.toInfoDTO(board.getMember()))
                    .restaurant(board.getRestaurant())
                    .build();
        }
    }
}
