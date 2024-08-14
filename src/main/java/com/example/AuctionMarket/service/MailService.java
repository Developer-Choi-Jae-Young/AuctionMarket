package com.example.AuctionMarket.service;

import com.example.AuctionMarket.repository.RedisRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private final RedisRepository redisRepository;
    private String code = createKey();
    private String id = "cjy951213@naver.com";
    private String name = "admin";

    public MimeMessage createMessage(String To)throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, To); // to 보내는 대상
        message.setSubject("ㅇㅇㅇ 회원가입 인증 코드: "); //메일 제목

        // 메일 내용 메일의 subtype을 html로 지정하여 html문법 사용 가능
        String msg="";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += code;
        msg += "</td></tr></tbody></table></div>";

        message.setText(msg, "utf-8", "html"); //내용, charset타입, subtype
        message.setFrom(new InternetAddress(id,name)); //보내는 사람의 메일 주소, 보내는 사람 이름

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    public String SendToMail(String To)
    {
        try
        {
            MimeMessage message = createMessage(To);
            redisRepository.setDateExpire(To, code, 180000);
            javaMailSender.send(message);
        }
        catch (MailException e)
        {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        return code;
    }

    public boolean validKey(String email, String key)
    {
        if(redisRepository.getData(email).equals(key)) {
            return true;
        } else {
            return false;
        }
    }
}
