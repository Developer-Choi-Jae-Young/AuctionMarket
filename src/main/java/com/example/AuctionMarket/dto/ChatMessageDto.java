package com.example.AuctionMarket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class ChatMessageDto {
    private String message;
    private String sender;
    private Date regDate;
}
