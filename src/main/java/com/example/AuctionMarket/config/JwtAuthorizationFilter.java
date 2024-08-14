package com.example.AuctionMarket.config;

import com.example.AuctionMarket.service.JwtToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private JwtToken jwtToken;

    public JwtAuthorizationFilter(JwtToken jwtToken)
    {
        this.jwtToken = jwtToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Authorization Filter");
        String accessJwt = jwtToken.resolveAccessToken(request);
        String refreshJwt = jwtToken.resolveRefreshToken(request);

        if(accessJwt != null && jwtToken.validateToken(accessJwt))
        {
            Authentication authentication = jwtToken.getAuthentication(accessJwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}