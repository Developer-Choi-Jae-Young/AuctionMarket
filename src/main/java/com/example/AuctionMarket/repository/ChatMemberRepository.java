package com.example.AuctionMarket.repository;

import com.example.AuctionMarket.entity.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
    List<ChatMember> findByMemberId(Long id);
}
