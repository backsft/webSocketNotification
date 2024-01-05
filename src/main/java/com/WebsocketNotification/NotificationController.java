package com.WebsocketNotification;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@MessageMapping("/sendNotification")
	@SendTo("/topic/notifications")
	public Notification sendNotificationMessage(Notification request) {
		Notification notification = createAndSendNotification(request.getMessage());
		return notification;
	}

	@PostMapping("/sendNotificationHttp")
	public void sendNotificationHttp(@RequestBody Notification request) {
		createAndSendNotification(request.getMessage());
	}

	@GetMapping("/notifications")
	public List<Notification> getAllNotifications() {
		return notificationService.getAllNotifications();
	}

	private Notification createAndSendNotification(String message) {
		Notification notification = new Notification();
		notification.setMessage(message);
		notificationService.sendNotification(notification);
		return notification;
	}
}
