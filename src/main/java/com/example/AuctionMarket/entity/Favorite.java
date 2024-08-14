package com.example.AuctionMarket.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Favorite {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
