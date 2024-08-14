package com.example.AuctionMarket.repository;

import com.example.AuctionMarket.entity.BbsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BbsCategoryRepository extends JpaRepository<BbsCategory, Long> {
}
