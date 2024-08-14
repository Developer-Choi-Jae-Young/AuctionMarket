package com.example.AuctionMarket.entity;

import com.example.AuctionMarket.entity.etc.OrderState;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Date;

@Entity
@Getter
public class ProductOrder {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderState orderState;
    private String address;
    private Date orderDate;
    private int count;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
