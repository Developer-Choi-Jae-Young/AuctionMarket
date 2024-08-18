package com.example.AuctionMarket.controller;

import com.example.AuctionMarket.dto.BoardDto;
import com.example.AuctionMarket.dto.BoardListDto;
import com.example.AuctionMarket.dto.BoardWriteDto;
import com.example.AuctionMarket.dto.CommentDto;
import com.example.AuctionMarket.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public List<BoardListDto> insert(Pageable boardPage) {
        return boardService.getBoardList(boardPage);
    }

    @GetMapping("/list/count")
    public Long countAll() {
        return boardService.countAll();
    }

    @GetMapping("/post/{id}")
    public BoardDto viewPost(@PathVariable Long id) {
        return boardService.getBoardById(id); // 기능 구현 전
    }

    @GetMapping("/post/{id}/comments")
    public List<CommentDto> viewPostComments(@PathVariable Long id) {
        return boardService.showPostComments(id); // 기능 구현 전
    }

    @PostMapping("/write")
    public boolean write(@RequestBody BoardWriteDto boardWriteDto, @RequestParam String id) {
        boardService.insertBoard(boardWriteDto, id);
        return true;
    }

    @PostMapping("/write/{id}/comment")
    public boolean commentWrite(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        boardService.insertBoardComment(id, commentDto);
        return true;
    }
}
