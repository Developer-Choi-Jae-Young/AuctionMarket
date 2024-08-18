package com.example.AuctionMarket.service;

import com.example.AuctionMarket.dto.BoardDto;
import com.example.AuctionMarket.dto.BoardListDto;
import com.example.AuctionMarket.dto.BoardWriteDto;
import com.example.AuctionMarket.dto.CommentDto;
import com.example.AuctionMarket.entity.Board;
import com.example.AuctionMarket.entity.Comment;
import com.example.AuctionMarket.repository.BoardRepository;
import com.example.AuctionMarket.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberService memberService;

    @Transactional
    public void insertBoard(BoardWriteDto boardWriteDto, String userName) {
       Board board = Board.builder()
                .regDate(boardWriteDto.getRegData())
                .title(boardWriteDto.getTitle())
                .context(boardWriteDto.getContext())
                .viewCount(0)
                .member(memberService.findByUserId(userName))
                .build();
        boardRepository.save(board);
    }

    @Transactional
    public void insertBoardComment(Long id, CommentDto commentDto) {
        Board boardEntity = boardRepository.findById(id).orElse(null);

        Comment commentEntity = Comment.builder()
                .member(memberService.findByUserId(commentDto.getWriter()))
                .board(boardEntity)
                .regDate(commentDto.getRegDate())
                .context(commentDto.getContext())
                .comment(commentDto.getParentId() > -1 ? commentRepository.findById(commentDto.getParentId()).orElse(null) : null)
                .build();

//        boardEntity.addComment(commentEntity);

        commentRepository.save(commentEntity);
    }

    @Transactional
    public List<CommentDto> showPostComments(Long id) {
        return commentRepository.findByBoard_Id(id).stream().map(CommentDto::of).toList();
    }

    public Long countAll() {
        return boardRepository.countBy();
    }

    public List<BoardListDto> getBoardList(Pageable boardPage) {
        return boardRepository.findAll(boardPage).stream().map(BoardListDto::of).toList();
    }

    @Transactional
    public BoardDto getBoardById(Long id) {
        return boardRepository.findById(id).map(BoardDto::of).orElse(null);
    }
}
