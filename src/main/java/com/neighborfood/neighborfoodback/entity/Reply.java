package com.neighborfood.neighborfoodback.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.neighborfood.neighborfoodback.dto.ReplyDTO;
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
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reply_no;

    private String contents;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;


    @JsonBackReference
    @JoinColumn(name = "member_no")
    @ManyToOne
    private Member member;

    @JsonBackReference
    @JoinColumn(name = "board_no")
    @ManyToOne
    private Board board;

    public static ReplyDTO.info toInfoDTO(Reply reply) {
        LocalDateTime regDate = reply.getReg_date();
        LocalDateTime modDate = reply.getMod_date();
        String convertedRegDate = regDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        if (modDate != null) {
            String convertedModDate = modDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
            return ReplyDTO.info.builder()
                    .reply_no(reply.getReply_no())
                    .contents(reply.getContents())
                    .reg_date(convertedRegDate)
                    .mod_date(convertedModDate)
                    .writer(reply.getMember().getName())
                    .build();
        }
        return ReplyDTO.info.builder()
                .reply_no(reply.getReply_no())
                .contents(reply.getContents())
                .reg_date(convertedRegDate)
                .writer(reply.getMember().getName())
                .build();
    }

    public static List<ReplyDTO.info> toInfoDTOList(List<Reply> replyList) {
        return replyList.stream().map(Reply::toInfoDTO).collect(Collectors.toList());
    }
}
