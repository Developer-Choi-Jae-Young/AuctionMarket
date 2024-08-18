package com.example.AuctionMarket.dto;

import com.example.AuctionMarket.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class CommentDto {
    private Long id;
    private Date regDate;
    private String context;
    private String writer;
    private Long parentId;

    public static CommentDto of(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .regDate(comment.getRegDate())
                .context(comment.getContext())
                .writer(comment.getMember().getName())
                .parentId(comment.getComment() == null ? -1 : comment.getComment().getId())
                .build();
    }
}
