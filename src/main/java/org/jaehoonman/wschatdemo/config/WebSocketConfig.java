package org.jaehoonman.wschatdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // STOMP를 기반으로하는 Web Socket 서버로 활용하기 위해.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Web Socket 통신 수립을 위한 엔드포인트, CORS, SockJS 등을 설정한다.
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws/chat") // Web Socket 연결 수립을 위한 End-point 설정.
                .setAllowedOrigins("http://localhost:8080/*") // CORS를 모두 허용한다.
                .withSockJS(); // SocketJS를 기반으로 설정한다.
    }

    /**
     * STOMP를 통해 메시지를 핸들링하기 위한 브로커를 설정한다.
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /** Server -> Client | 해당 경로를 Subscribe 하는 Client들에게 메시지 보낸다.
         * 서버에서 Client로 Message를 보내는 경로이기 때문에 사용자가 실시간으로 Message를 receive하기 위해서는
         * '/topic' 으로 시작하는 WS 경로를 subscribe 해야한다.
         */
        registry.enableSimpleBroker("/topic");

        /** Client -> Server | Client가 보낸 메시지를 Broking 하는 경로를 설정.
         *  Client가 Server로 Message를 보내는 경로이기에 sendMessage의 경로는
         *  '/app'를 포함하는 경로에 send 해야한다.
         */

        registry.setApplicationDestinationPrefixes("/app");


    }
}
