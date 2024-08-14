package com.example.AuctionMarket.service;

import com.example.AuctionMarket.dto.ProductDto;
import com.example.AuctionMarket.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void insertProduct(ProductDto productDto) {
        //productRepository.save();
    }
}
