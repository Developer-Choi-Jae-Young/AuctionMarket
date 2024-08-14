package com.example.AuctionMarket.controller;

import com.example.AuctionMarket.dto.MemberDto;
import com.example.AuctionMarket.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "로그인", description = "로그인 관련 api 입니다.")
@RestController
@RequestMapping("/usr")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final MemberService memberService;
    private final MailService mailService;
    private final SmsService smsService;
    private final JwtToken jwtToken;

    @Operation(summary = "회원가입 요청", description = "회원가입", tags = { "User Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/insert")
    public void insert(@RequestBody MemberDto memberDto) {
        memberService.insertMember(memberDto);
    }

    @GetMapping("/insert/mail")
    public void sendMail(@RequestParam String email) {
        mailService.SendToMail(email);
    }

    @GetMapping("/insert/mail/vaild")
    public boolean vaildMailKey(@RequestParam String email, @RequestParam String key) {
         return mailService.validKey(email,key);
    }

    @GetMapping("/insert/user/vaild")
    public boolean vaildUser(@RequestParam String userName) {
        log.info(userName);
        return memberService.duplicateUserName(userName);
    }

    @GetMapping("/insert/phone")
    public void sendPhone(@RequestParam String phone) {
        //smsService.SmsNumber(phone);
    }

    @GetMapping("/insert/phone/vaild")
    public boolean vaildPhoneKey(@RequestParam String phone, @RequestParam String key) {
        //return smsService.vaildSmsNumber(phone,key);
        return true;
    }

    @GetMapping("/insert/refresh-token")
    public void createRefreshToken(@RequestHeader(JwtProperties.HEADER_REFRESH_STRING) String refreshJwt, HttpServletResponse response) {
        if(refreshJwt != null && jwtToken.validateToken(refreshJwt)) {
            String access = jwtToken.accessTokenCreateToken(jwtToken.getAuthentication(refreshJwt));

            response.addHeader(JwtProperties.HEADER_ACCESS_STRING, JwtProperties.TOKEN_PREFIX + access);
            response.addHeader(JwtProperties.HEADER_REFRESH_STRING, JwtProperties.TOKEN_PREFIX + refreshJwt);
        }
    }
}
