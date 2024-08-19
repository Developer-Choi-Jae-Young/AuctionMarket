package com.example.AuctionMarket.controller;

import com.example.AuctionMarket.dto.*;
import com.example.AuctionMarket.service.ChattingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/chatting")
@RequiredArgsConstructor
@Slf4j
public class ChattingController {
    private final ChattingService chattingService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/insert")
    public Long insert(@RequestBody ChattingRoomDto chattingRoomDto) {
        return chattingService.insertChattingRoom(chattingRoomDto);
    }

    @PostMapping("/join")
    public void add(@RequestBody ChattingRoomJoinDto chattingRoomJoinDto) {
        chattingService.joinChattingRoom(chattingRoomJoinDto);
    }

    @GetMapping("/list")
    public List<ChatRoomListDto> insert(Pageable chattingRoomPage) {
        return chattingService.getChattingRoomList(chattingRoomPage);
    }

    @GetMapping("/list/count")
    public Long countAll() {
        return chattingService.countAll();
    }

    @GetMapping("/user/list")
    public List<ChatRoomListDto> viewUserList(@RequestParam String userId) {
        return chattingService.getChattingListByUserId(userId);
    }

    @GetMapping("/getlist")
    public List<Long> getListForUserId(@RequestParam String userId) {
        return chattingService.getListForUserId(userId);
    }

    @MessageMapping("chat.enter.{chatRoomId}")
    public void send(ChatMessageDto chat, @DestinationVariable String chatRoomId){
        chat.setRegDate(LocalDateTime.now());
        simpMessagingTemplate.convertAndSend("/topic/sample.exchange.room." + chatRoomId, chat); // exchange
    }
}
