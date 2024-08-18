package com.example.AuctionMarket.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BoardWriteDto {
    private Date regData;
    private String title;
    private String context;
}
