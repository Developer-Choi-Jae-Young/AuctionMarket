package com.example.AuctionMarket.repository;

import com.example.AuctionMarket.entity.Board;
import com.example.AuctionMarket.entity.ChattingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
    Page<ChattingRoom> findAll(Pageable pageable);
    Long countBy();

    List<ChattingRoom> findByIdIn(List<Long> id);
}
