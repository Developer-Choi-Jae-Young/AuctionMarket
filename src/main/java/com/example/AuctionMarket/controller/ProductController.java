package com.example.AuctionMarket.controller;

import com.example.AuctionMarket.dto.ProductDto;
import com.example.AuctionMarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/insert")
    public void insert(@RequestBody ProductDto productDto) {
        productService.insertProduct(productDto);
    }
}
