package com.WebsocketNotification;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
public class Notification {

	@Id
	private String id;
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Notification(String id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public Notification() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", message=" + message + "]";
	}

}
