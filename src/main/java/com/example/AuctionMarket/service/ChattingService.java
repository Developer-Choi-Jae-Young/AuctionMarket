package com.example.AuctionMarket.service;

import com.example.AuctionMarket.dto.ChattingRoomDto;
import com.example.AuctionMarket.repository.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChattingService {
    private final ChattingRoomRepository chattingRoomRepository;

    public void insertChattingRoom(ChattingRoomDto chattingRoomDto) {
        //chattingRoomRepository.save();
    }
}
