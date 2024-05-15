package org.jaehoonman.wschatdemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jaehoonman.wschatdemo.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StompChatController {

    /**
     * STOMP 기반의 메시지 처리 브로커.
     */

    // 웹 소켓 메시징 기능을 사용하기 위한.
    private final SimpMessageSendingOperations sendingOperations;

    /**
     * Client가 메시지를 보내는 경로와 메시지를 처리하는 로직
     * config 에서 setApplicationDestinationPrefixes("/app")를 설정했기에
     * 클라이언트는 `/app`을 포하한 경로로 메시지를 전송해야한다.
     * @param message
     */
    @MessageMapping("/chat/sendMessage")
    public void sendMessage(ChatMessage message) {

        // 메시지 타입에 따른 메시지 빌딩
        //최초 입장 시
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }
        // 퇴장 시
        else if (ChatMessage.MessageType.LEAVE.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 퇴장하였습니다.");
        }

        // "/topic/chat/room/" 의 경로로 메시지를 보내면 아래의 handleMessages() 가 처리를한다.
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }

    /**
     * 송수신 되는 메시지의 브로커.
     * "/topic/chat/room/ + id" 를 구독하는 사용자에게 메시지를 전송한다.
     * @param messages
     * @return
     */
    @MessageMapping("/topic/chat/room/{roomId}")
    @SendTo("/topic/chat/room/{roomId}")
    public List<ChatMessage> handleMessages(List<ChatMessage> messages) {
        return messages;
    }
}
