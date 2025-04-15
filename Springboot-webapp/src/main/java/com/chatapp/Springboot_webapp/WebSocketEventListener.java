package com.chatapp.Springboot_webapp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import static com.chatapp.Springboot_webapp.ChatMessagePojo.MessageType.LEAVE;

public class WebSocketEventListener {
    private static final Logger logger =  LoggerFactory.getLogger(WebSocketEventListener.class);


    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketListener(SessionConnectedEvent event) {
        logger.info("Received a new session connected event");
    }


    @EventListener
    public void handleWebSocketDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null){
            logger.info("User Disconnected " + username);
            ChatMessagePojo chatMessagePojo = new ChatMessagePojo();

            chatMessagePojo.setType(chatMessagePojo.getMessageType(LEAVE));
            chatMessagePojo.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessagePojo);
        }
    }


}
