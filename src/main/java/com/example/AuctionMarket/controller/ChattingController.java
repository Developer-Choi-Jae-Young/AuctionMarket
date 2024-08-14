package com.example.AuctionMarket.controller;

import com.example.AuctionMarket.dto.ChattingRoomDto;
import com.example.AuctionMarket.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatting")
@RequiredArgsConstructor
public class ChattingController {
    private final ChattingService chattingService;

    @PostMapping("/insert")
    public void insert(@RequestBody ChattingRoomDto chattingRoomDto) {
        chattingService.insertChattingRoom(chattingRoomDto);
    }
}
