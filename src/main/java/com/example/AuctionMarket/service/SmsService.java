package com.example.AuctionMarket.service;

import com.example.AuctionMarket.config.SmsConfiguration;
import com.example.AuctionMarket.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsService {
    private final SmsConfiguration smsConfiguration;
    private final RedisRepository redisRepository;

    public String SmsNumber(String phoneNumber)
    {
        String keyCode = createKey();
        smsConfiguration.sendOne(phoneNumber, keyCode);
        redisRepository.setDateExpire(phoneNumber, keyCode, 60);
        return keyCode;
    }

    public Boolean vaildSmsNumber(String phoneNumber, String keyCode)
    {
        if(redisRepository.isExistData(phoneNumber))
        {
            if(redisRepository.getData(phoneNumber).equals(keyCode))
            {
                return true;
            }
        }
        return false;
    }

    private String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }
}
