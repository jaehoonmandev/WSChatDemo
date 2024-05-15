package org.jaehoonman.wschatdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    /**
     * 채팅에 사용되는 메시지를 다루기위한 객체.
     */

    /**
     * 메시지 타입에 따라 액션 핸들링이 달라진다
     * ENTER : 입장.
     * TALK : 메시지 전송.
     * LEAVE : 퇴장.
     */
    public enum MessageType{
        ENTER, TALK, LEAVE;
    }

    private MessageType type; // 메시지 타입
    private String roomId; // 채팅방 번호
    private String sender; // 메시지 전송자
    private String message; // 메시지

}
