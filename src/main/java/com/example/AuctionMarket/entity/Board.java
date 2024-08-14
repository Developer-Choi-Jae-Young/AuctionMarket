package com.example.AuctionMarket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Board extends Bbs{

    @OneToMany(mappedBy = "board")
    private List<Favorite> favorites = new ArrayList<>();
    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();
}
