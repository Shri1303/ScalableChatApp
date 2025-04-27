var socket = null;

function connect() {
    socket = new WebSocket('ws://' + window.location.host + '/ws');

    socket.onopen = function (event) {
        console.log('Connected to WebSocket server');
    };

    socket.onmessage = function (event) {
        var message = JSON.parse(event.data);
        showMessage(message);
    };

    socket.onclose = function (event) {
        console.log('Disconnected from WebSocket server');
    };

    socket.onerror = function (error) {
        console.error('WebSocket error: ', error);
    };
}

function sendMessage() {
    var messageContent = document.getElementById('message').value.trim();
    if (messageContent && socket && socket.readyState === WebSocket.OPEN) {
        var chatMessage = {
            sender: "User",
            content: messageContent,
            type: 'CHAT'
        };
        socket.send(JSON.stringify(chatMessage));
        document.getElementById('message').value = '';
    }
}

function showMessage(message) {
    var messageArea = document.getElementById('messageArea');
    var messageElement = document.createElement('p');
    messageElement.appendChild(document.createTextNode(message.sender + ": " + message.content));
    messageArea.appendChild(messageElement);
}

document.getElementById('messageForm').addEventListener('submit', function (e) {
    e.preventDefault();
    sendMessage();
});

connect();