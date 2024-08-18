package com.example.AuctionMarket.entity;

import com.example.AuctionMarket.entity.etc.TypeCategory;
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
public class BbsCategory {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeCategory typeCategory;

    @Builder.Default
    @OneToMany(mappedBy = "bbsCategory")
    private List<Bbs> bbs = new ArrayList<>();
}
