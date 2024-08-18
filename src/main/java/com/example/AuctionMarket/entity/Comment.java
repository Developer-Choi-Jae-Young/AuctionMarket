package com.example.AuctionMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private Date regDate;
    private String context;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
    @ManyToOne
    @JoinColumn(name = "commant_id")
    private Comment comment;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private  Member member;

    @Builder.Default
    @OneToMany(mappedBy = "comment")
    private List<Comment> comments = new ArrayList<>();

    public void setBoard(Board board) {
        this.board = board;
    }
}
