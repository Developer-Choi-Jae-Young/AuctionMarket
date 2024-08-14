package com.example.AuctionMarket.repository;

import com.example.AuctionMarket.entity.BbsFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BbsFileRepository extends JpaRepository<BbsFile, Long> {
}
