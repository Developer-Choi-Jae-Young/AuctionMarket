package com.example.AuctionMarket.service;

public class JwtProperties {
    public static final String HEADER_ACCESS_STRING = "ACCESS_TOKEN";         // secret key
    public static final String HEADER_REFRESH_STRING = "REFRESH_TOKEN";         // secret key
//  public static final String TOKEN_PREFIX = "Bearer "; // 왜 있는지 모르겠음 ㅋㅋ
    public static final String TOKEN_PREFIX = "";
    public static final String HEADER_STRING = "Authorization";
}
