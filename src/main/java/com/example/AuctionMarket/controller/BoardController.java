package com.example.AuctionMarket.controller;

import com.example.AuctionMarket.dto.BoardDto;
import com.example.AuctionMarket.dto.BoardListDto;
import com.example.AuctionMarket.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/insert")
    public void insert(@RequestBody BoardDto boardDto) {
        boardService.insertBoard(boardDto);
    }

    @GetMapping("/list")
    public List<BoardListDto> insert(Pageable boardPage) {
        return boardService.getBoardList(boardPage);
    }
}
