package com.example.AuctionMarket.dto;

import com.example.AuctionMarket.entity.etc.Grade;
import lombok.Getter;

import java.sql.Date;

@Getter
public class MemberDto {
    private String userId;
    private String password;
    private String email;
    private String name;
    private char gender;
    private Date birthDay;
    private String phone;
    private int point;
    private Grade grade;
    private Date regDate;
}
