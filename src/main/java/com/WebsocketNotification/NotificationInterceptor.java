package com.WebsocketNotification;

import java.util.List;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class NotificationInterceptor implements ChannelInterceptor {

    private final NotificationService notificationService;

    public NotificationInterceptor(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            // Send past notifications to the user upon connection
            List<Notification> pastNotifications = notificationService.getAllNotifications();
            pastNotifications.forEach(notification -> {
                SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create();
                headerAccessor.setSessionId(accessor.getSessionId());
                headerAccessor.setLeaveMutable(true);

                notificationService.sendNotification(notification);
            });
        }
        return message;
    }
}
