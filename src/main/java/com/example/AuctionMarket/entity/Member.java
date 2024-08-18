package com.example.AuctionMarket.entity;

import com.example.AuctionMarket.entity.etc.Grade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String userId;
    private String password;
    private String email;
    private String name;
    @Column(columnDefinition = "VARCHAR(60) CHECK (gender IN ('M', 'F'))")
    private char gender;
    private Date birthDay;
    private String phone;
    private int point;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    private Date regDate;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Favorite> favorites = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Cart> carts = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<ChatMember> chatMembers = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Bbs> bbs = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<ProductOrder> productOrders = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Member(String userId, String password, String email, String name, char gender, Date birthDay, String phone, int point, Grade grade, Date regDate) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.phone = phone;
        this.point = point;
        this.grade = grade;
        this.regDate = regDate;
    }
}
