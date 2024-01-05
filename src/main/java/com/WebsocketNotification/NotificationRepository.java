package com.WebsocketNotification;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
	
	
	  
	  List<Notification> findAllByOrderByIdDesc();
	  
	  
}
