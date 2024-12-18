package com.example.AuctionMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BbsFile {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "bbs_id")
    private Bbs bbs;
}
