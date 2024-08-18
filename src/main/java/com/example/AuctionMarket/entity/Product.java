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
public class Product extends Bbs{
    private int price;
    private int amount;
    private Date endDate;

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<ProductOrder> productOrders = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<Cart> carts = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<ChattingRoom> chattingRooms = new ArrayList<>();
}
