package com.WebsocketNotification;

import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	private final SimpMessagingTemplate messagingTemplate;
	private final NotificationRepository notificationRepository;

	public NotificationService(SimpMessagingTemplate messagingTemplate, NotificationRepository notificationRepository) {
		this.messagingTemplate = messagingTemplate;
		this.notificationRepository = notificationRepository;
	}

	public void sendNotification(Notification notification) {
		// Save notification to MongoDB
		notificationRepository.save(notification);

		// Send notification to WebSocket
		messagingTemplate.convertAndSend("/topic/notifications", notification);
	}

	public List<Notification> getAllNotifications() {
		return notificationRepository.findAll();
	}
	
	
	
}
