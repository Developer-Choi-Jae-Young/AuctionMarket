package com.example.AuctionMarket.service;

import com.example.AuctionMarket.dto.BoardDto;
import com.example.AuctionMarket.dto.BoardListDto;
import com.example.AuctionMarket.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void insertBoard(BoardDto boardDto) {
        //boardRepository.save();
    }

    public List<BoardListDto> getBoardList(Pageable boardPage) {
        return boardRepository.findAll(boardPage).stream().map(BoardListDto::of).toList();
    }
}
