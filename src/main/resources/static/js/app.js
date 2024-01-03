var stompClient = null;

function connect() {
    var socket = new SockJS('/websocket-demo');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/notifications', function (notification) {
            showNotification(JSON.parse(notification.body).message);
        });
    });
}

function showNotification(message) {
    var notificationContainer = document.getElementById('notification-container');
    var notificationElement = document.createElement('div');
    notificationElement.className = 'notification';
    notificationElement.textContent = message;
    notificationContainer.appendChild(notificationElement);
}

document.addEventListener('DOMContentLoaded', function () {
    connect();
});
