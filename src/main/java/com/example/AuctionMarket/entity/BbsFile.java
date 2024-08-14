package com.example.AuctionMarket.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class BbsFile {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "bbs_id")
    private Bbs bbs;
}
