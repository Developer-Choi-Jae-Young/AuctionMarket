package com.example.AuctionMarket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Getter
public class Board extends Bbs{
    @Builder.Default
    @OneToMany(mappedBy = "board")
    private List<Favorite> favorites = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comment.setBoard(this);
        comments.add(comment);
    }

    public  Board() {
    }

    @Builder
    public Board(Date regDate, String context, String title, int viewCount, Member member) {
        super(regDate, context, title, viewCount, member);
    }
}
