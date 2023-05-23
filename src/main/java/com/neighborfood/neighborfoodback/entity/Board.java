package com.neighborfood.neighborfoodback.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.neighborfood.neighborfoodback.dto.BoardDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private String location;
    private LocalDateTime order_time;
    private Integer max_people;
    private Integer cur_people;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Participation> participationList;

    @JsonManagedReference
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Basket> basketList;

    public static BoardDTO.info toInfoDTO(Board board) {
        LocalDateTime regDate = board.getReg_date();
        LocalDateTime modDate = board.getMod_date();
        String convertedRegDate = regDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm"));
        if (modDate != null) {
            String convertedModDate = modDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm"));
            return BoardDTO.info.builder()
                    .board_no(board.getBoard_no())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .category(board.getCategory())
                    .latitude(board.getLatitude())
                    .longitude(board.getLongitude())
                    .location(board.getLocation())
                    .order_time(board.getOrder_time())
                    .max_people(board.getMax_people())
                    .cur_people(board.getCur_people())

                    .reg_date(convertedRegDate)
                    .mod_date(convertedModDate)

                    .member(Member.toInfoDTO(board.getMember()))
                    .restaurant(board.getRestaurant())

                    .build();
        }
        return BoardDTO.info.builder()
                .board_no(board.getBoard_no())
                .title(board.getTitle())
                .contents(board.getContents())
                .category(board.getCategory())
                .latitude(board.getLatitude())
                .longitude(board.getLongitude())
                .location(board.getLocation())
                .order_time(board.getOrder_time())
                .max_people(board.getMax_people())
                .cur_people(board.getCur_people())

                .reg_date(convertedRegDate)

                .member(Member.toInfoDTO(board.getMember()))
                .restaurant(board.getRestaurant())

                .build();
    }

    public static List<BoardDTO.info> toInfoDTOList(List<Board> boardList) {
        return boardList.stream().map(Board::toInfoDTO).collect(Collectors.toList());
    }

    public static BoardDTO.detail toDetailDTO(Board board) {
        LocalDateTime regDate = board.getReg_date();
        LocalDateTime modDate = board.getMod_date();
        String convertedRegDate = regDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm"));
        if (modDate != null) {
            String convertedModDate = modDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm"));
            return BoardDTO.detail.builder()
                    .board_no(board.getBoard_no())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .category(board.getCategory())
                    .latitude(board.getLatitude())
                    .longitude(board.getLongitude())
                    .location(board.getLocation())
                    .order_time(board.getOrder_time())
                    .max_people(board.getMax_people())
                    .cur_people(board.getCur_people())

                    .reg_date(convertedRegDate)
                    .mod_date(convertedModDate)

                    .participant(Participation.toInfoDTOList(board.getParticipationList()))
                    .reply(Reply.toInfoDTOList(board.getReplyList()))
                    .member(Member.toInfoDTO(board.getMember()))
                    .restaurant(board.getRestaurant())
                    .build();
        }
        return BoardDTO.detail.builder()
                .board_no(board.getBoard_no())
                .title(board.getTitle())
                .contents(board.getContents())
                .category(board.getCategory())
                .latitude(board.getLatitude())
                .longitude(board.getLongitude())
                .location(board.getLocation())
                .order_time(board.getOrder_time())
                .max_people(board.getMax_people())
                .cur_people(board.getCur_people())

                .reg_date(convertedRegDate)

                .participant(Participation.toInfoDTOList(board.getParticipationList()))
                .reply(Reply.toInfoDTOList(board.getReplyList()))
                .member(Member.toInfoDTO(board.getMember()))
                .restaurant(board.getRestaurant())
                .build();
    }
}
