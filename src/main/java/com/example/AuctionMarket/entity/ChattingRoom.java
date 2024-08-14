package com.example.AuctionMarket.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ChattingRoom {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
