package com.example.AuctionMarket.dto;

import com.example.AuctionMarket.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@Builder
public class BoardDto {
    private Long id;
    private Date regDate;
    private String context;
    private String title;
    private int viewCount;
    private String writer;
    private List<CommentDto> commentDtos;

    public static BoardDto of(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .regDate(board.getRegDate())
                .context(board.getContext())
                .title(board.getTitle())
                .viewCount(board.getViewCount())
                .writer(board.getMember().getName())
                .commentDtos(board.getComments().stream().map(CommentDto::of).toList())
                .build();
    }
}
