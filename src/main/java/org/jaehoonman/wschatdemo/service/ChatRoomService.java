package org.jaehoonman.wschatdemo.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jaehoonman.wschatdemo.model.ChatRoom;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatRoomService {

    /**
     * 채팅방의 생성, 조회를 담당하는 서비스 레이어.
     */

    // 채팅 정보를 K:V의 구조로 메모리에 저장하기 위한 Map
    private Map<String, ChatRoom> chatRooms;

    // 초기화 시 Map을 인스턴스화 시켜준다.
    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    // Map에 저장된 모든 데이터들을 가져온다.
    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    //K:V의 구조로 Key 값에 해당하는 Value를 하나 가져온다.
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    // 방을 생성한다.
    public ChatRoom createRoom(String roomName) {
        ChatRoom chatRoom = new ChatRoom(roomName);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

}
