package com.example.AuctionMarket.service;

import com.example.AuctionMarket.entity.Member;
import com.example.AuctionMarket.entity.UserDetailsImpl;
import com.example.AuctionMarket.repository.MemberRepository;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return new UserDetailsImpl(member);
    }
}
