package com.WebsocketNotification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notificationsendtest")
    public String sendTestNotification() {
        Notification notification = new Notification("Test Notification");
        notificationService.sendNotification(notification);
        return "Notification sent!";
    }
}
