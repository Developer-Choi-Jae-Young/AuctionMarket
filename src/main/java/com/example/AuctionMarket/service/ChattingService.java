package com.example.AuctionMarket.service;

import com.example.AuctionMarket.dto.BoardListDto;
import com.example.AuctionMarket.dto.ChatRoomListDto;
import com.example.AuctionMarket.dto.ChattingRoomDto;
import com.example.AuctionMarket.dto.ChattingRoomJoinDto;
import com.example.AuctionMarket.entity.ChatMember;
import com.example.AuctionMarket.entity.ChattingRoom;
import com.example.AuctionMarket.repository.ChatMemberRepository;
import com.example.AuctionMarket.repository.ChattingRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingService {
    private final ChattingRoomRepository chattingRoomRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final MemberService memberService;

    @Transactional
    public Long insertChattingRoom(ChattingRoomDto chattingRoomDto) {
        ChattingRoom chattingRoomEntity = ChattingRoom.builder().title(chattingRoomDto.getTitle()).build();
        chattingRoomRepository.save(chattingRoomEntity);

        ChatMember chatMemberEntity = ChatMember.builder().chattingRoom(chattingRoomEntity).member(memberService.findByUserId(chattingRoomDto.getUserId())).build();
        return chatMemberRepository.save(chatMemberEntity).getId();
    }

    @Transactional
    public Long joinChattingRoom(ChattingRoomJoinDto chattingRoomJoinDto) {
        ChatMember chatMemberEntity = ChatMember.builder().chattingRoom(chattingRoomRepository.findById(chattingRoomJoinDto.getChatRoomId()).orElse(null)).member(memberService.findByUserId(chattingRoomJoinDto.getUserId())).build();

        return chatMemberRepository.save(chatMemberEntity).getId();
    }

    public List<ChatRoomListDto> getChattingRoomList(Pageable chattingRoomPage) {
        return chattingRoomRepository.findAll(chattingRoomPage).stream().map(ChatRoomListDto::of).toList();
    }

    public Long countAll() {
        return chattingRoomRepository.countBy();
    }

    public List<Long> getListForUserId(String userId) {
        return chatMemberRepository.findByMemberId(memberService.findByUserId(userId).getId()).stream().map(ChatMember::getId).toList();
    }

    @Transactional
    public List<ChatRoomListDto> getChattingListByUserId(String userId) {
        List<ChattingRoom> chattingRoomList = chatMemberRepository.findByMemberId(memberService.findByUserId(userId).getId()).stream().map(ChatMember::getChattingRoom).toList();
        List<Long> chattingIdList = chattingRoomList.stream().map(ChattingRoom::getId).toList();
        return chattingRoomRepository.findByIdIn(chattingIdList).stream().map(ChatRoomListDto::of).toList();
    }
}
