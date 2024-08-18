package com.example.AuctionMarket.controller;

import com.example.AuctionMarket.dto.*;
import com.example.AuctionMarket.service.ChattingService;
import com.example.AuctionMarket.service.RabbitMQService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chatting")
@RequiredArgsConstructor
@Slf4j
public class ChattingController {
    private final ChattingService chattingService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RabbitMQService messageService;

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
    @MessageMapping("/chat/send")
    public void sendMsg(@Payload Map<String,Object> data){
        simpMessagingTemplate.convertAndSend("/topic/1",data);
    }

    /**
     * Queue로 메시지를 발행
     *
     * @param messageDto 발행할 메시지의 DTO 객체
     * @return ResponseEntity 객체로 응답을 반환
     */
    @PostMapping("/send/message")
    public boolean sendMessage(@RequestBody ChatMessageDto messageDto) {
        messageService.sendMessage(messageDto);
        return true;
    }
}
