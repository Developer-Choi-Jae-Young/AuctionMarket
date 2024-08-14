package com.example.AuctionMarket.service;

import com.example.AuctionMarket.dto.MemberDto;
import com.example.AuctionMarket.entity.Member;
import com.example.AuctionMarket.entity.etc.Grade;
import com.example.AuctionMarket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Transactional
    public void insertMember(MemberDto member) {
        Member memberEntity = Member.builder().
                userId(member.getUserId()).
                password(passwordEncoder.encode(member.getPassword())).
                email(member.getEmail()).
                name(member.getName()).
                gender(member.getGender()).
                birthDay(member.getBirthDay()).
                phone(member.getPhone()).
                point(0).
                grade(Grade.USER).
                regDate(Date.valueOf(LocalDate.now())).build();
        memberRepository.save(memberEntity);
    }

    public void sendEmail(String To) {
        //이렇게 안하면 메일 전송하고 완료된 후에 넘어가는데 그게 3초정도 걸린다고함. 그래서 비동기적으로 처리
        CompletableFuture.runAsync(() -> {
            try {
                mailService.SendToMail(To);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public boolean duplicateUserName(String userName) {
        if(memberRepository.findByUserId(userName).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
