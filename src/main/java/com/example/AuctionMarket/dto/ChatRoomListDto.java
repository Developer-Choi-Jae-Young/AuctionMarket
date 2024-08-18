package com.example.AuctionMarket.dto;

import com.example.AuctionMarket.entity.ChattingRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatRoomListDto {
    private Long id;
    private String title;

    public static ChatRoomListDto of(ChattingRoom chattingRoom) {
        return ChatRoomListDto.builder()
                .id(chattingRoom.getId())
                .title(chattingRoom.getTitle())
                .build();
    }
}
