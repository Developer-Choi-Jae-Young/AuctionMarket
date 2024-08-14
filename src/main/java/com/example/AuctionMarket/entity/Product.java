package com.example.AuctionMarket.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Product extends Bbs{
    private int price;
    private int amount;
    private Date endDate;

    @OneToMany(mappedBy = "product")
    private List<ProductOrder> productOrders = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Cart> carts = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<ChattingRoom> chattingRooms = new ArrayList<>();
}
