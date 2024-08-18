package com.example.AuctionMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChattingRoom {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @Builder.Default
    @OneToMany(mappedBy = "chattingRoom")
    private List<ChatMember> chatMembers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
