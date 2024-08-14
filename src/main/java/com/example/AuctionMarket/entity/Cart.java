package com.example.AuctionMarket.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Cart {
    @Id
    @GeneratedValue
    private Long id;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
