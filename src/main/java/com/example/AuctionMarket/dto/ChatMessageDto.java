package com.example.AuctionMarket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDto {
    private String message;
    private String sender;
    private LocalDateTime regDate;
}
