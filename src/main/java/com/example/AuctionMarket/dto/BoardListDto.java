package com.example.AuctionMarket.dto;

import com.example.AuctionMarket.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoardListDto {
    private Long id;
    private String title;

    public static BoardListDto of(Board board) {
        return BoardListDto.builder().id(board.getId()).title(board.getTitle()).build();
    }
}
