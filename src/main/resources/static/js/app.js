var stompClient = null;

function connect() {
    var socket = new SockJS('/websocket-demo');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        // Clear existing subscriptions
        clearSubscriptions();

        // Subscribe to the topic
        stompClient.subscribe('/topic/notifications', function (notification) {
            // Handle new notifications from WebSocket
            handleNotification(JSON.parse(notification.body));
        });

        // Fetch and handle existing notifications from the server when connected
        fetchNotifications();
    });
}

function clearSubscriptions() {
    if (stompClient && stompClient.subscriptions) {
        Object.keys(stompClient.subscriptions).forEach(function (subscriptionId) {
            stompClient.unsubscribe(subscriptionId);
        });
    }
}

function fetchNotifications() {
    // Fetch notifications from the server (assuming you have a REST endpoint for this)
    // Adjust the URL based on your backend configuration
    fetch('/notifications')
        .then(response => response.json())
        .then(notifications => {
            // Handle the retrieved notifications
            notifications.forEach(notification => {
                handleNotification(notification);
            });
        })
        .catch(error => console.error('Error fetching notifications:', error));
}

function handleNotification(notification) {
    // Handle the notification as needed
    // For example, you can add them to an array or display them in a different way
 //   console.log('Received notification:', notification);

    // Display the notification in the HTML (you can customize this part)
    var notificationContainer = document.getElementById('notification-container');
    var notificationElement = document.createElement('div');
    notificationElement.className = 'notification';
    notificationElement.textContent = notification.message;
    
   
   notificationContainer.appendChild(notificationElement);

    
    
}

/*

function sendNotification() {
    var notificationInput = document.getElementById('notification-input');
    var notificationMessage = notificationInput.value;
    var notification = { message: notificationMessage };

   // stompClient.send("/app/sendNotification", {}, JSON.stringify(notification));
   stompClient.send("/app/sendNotification", {}, JSON.stringify(notification));
   
    notificationInput.value = '';
}

*/

document.addEventListener('DOMContentLoaded', function () {
    connect();
});
