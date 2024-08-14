package com.example.AuctionMarket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
@Setter
public class Bbs {
    @Id
    @GeneratedValue
    private Long id;
    private Date regDate;
    private String context;
    private String title;
    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private BbsCategory bbsCategory;

    @OneToMany(mappedBy = "bbs")
    List<BbsFile> bbsFiles = new ArrayList<>();
}
