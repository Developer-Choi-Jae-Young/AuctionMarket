package com.example.AuctionMarket.entity;

import com.example.AuctionMarket.entity.etc.TypeCategory;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class BbsCategory {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeCategory typeCategory;

    @OneToMany(mappedBy = "bbsCategory")
    private List<Bbs> bbs = new ArrayList<>();
}
