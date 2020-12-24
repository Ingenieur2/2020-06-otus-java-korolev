let stompClient = null;

const setConnected = (connected) => {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#usersList").show();
    } else {
        $("#usersList").hide();
    }
    $("#usersTable > tbody").empty();
}

const connect = () => {
    stompClient = Stomp.over(new SockJS('/websocket'));
    stompClient.connect({}, (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/users', (message) => showMessage(JSON.parse(message.body)));
    });
}

const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMsg() {
        stompClient.send("/app/chat.addUser", {}, JSON.stringify({
            id: $("#userId").val(),
            name: $("#userName").val(),
            age: $("#userAge").val(),
            login: $("#userLogin").val(),
            password: $("#userPassword").val()
        }));
    $("#create-form")[0].reset();
}

const showMessage = (user) => {
    if ((user.id === 0) ||
        (user.name === "") ||
        (user.age === 0) ||
        (user.login === "") ||
        (user.password === "")) {
        $("#usersStr").append();
    } else {
        $("#usersStr").append("<tr>" +
            "<td>" + user.id + "</td>" +
            "<td>" + user.name + "</td>" +
            "<td>" + user.age + "</td>" +
            "<td>" + user.login + "</td>" +
            "<td>" + user.password + "</td>" +
            "</tr>")
    }
}