package com.neighborfood.neighborfoodback.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.neighborfood.neighborfoodback.dto.BoardDTO;
import com.neighborfood.neighborfoodback.dto.ParticipationDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer participation_no;

    @JsonBackReference
    @JoinColumn(name = "board_no")
    @ManyToOne
    private Board board;

    @JsonBackReference
    @JoinColumn(name = "member_no")
    @ManyToOne
    private Member member;

    public static ParticipationDTO.info toInfoDTO(Participation participation) {
        return ParticipationDTO.info.builder()
                .participation_no(participation.getParticipation_no())
                .board_no(participation.getBoard().getBoard_no())
                .participant_name(participation.getMember().getName())
                .build();
    }

    public static List<ParticipationDTO.info> toInfoDTOList(List<Participation> participationList) {
        return participationList.stream().map(Participation::toInfoDTO).collect(Collectors.toList());
    }
}
