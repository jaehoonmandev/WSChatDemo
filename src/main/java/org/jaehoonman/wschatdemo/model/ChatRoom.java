package org.jaehoonman.wschatdemo.model;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.UUID;


@Data
public class ChatRoom {

    /**
     * 채팅을 위해 생성된 방의 정보
     */
    private String roomId; // 채팅방 ID
    private String roomName; // 채팅방 이름

    /**
     * 생성자.
     * ChatRoom을 new하여 신규 방을 생성할 시 랜덤 ID와 생성할 방 이름을 저장한다.
     * @param roomName
     */
    public ChatRoom(String roomName){
        this.roomId = UUID.randomUUID().toString();
        this.roomName = roomName;
    }
}
