let stompClient = null;

const setConnected = (connected) => {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#usersList").show();
        $("#tests").show();
        $("#results").show();
    } else {
        $("#usersList").hide();
        $("#tests").hide();
        $("#results").hide();
    }
    $("#usersTable > tbody").empty();
    $("#testsTable > tbody").empty();
    sendMsgAll();
}

const connect = () => {
    stompClient = Stomp.over(new SockJS('/websocket'));
    stompClient.connect({}, (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/getAll', (message) => showAll(JSON.parse(message.body)));
        stompClient.subscribe('/topic/users', (message) => showMessage(JSON.parse(message.body)));
        stompClient.subscribe('/topic/getAllQuestions', (message) => showTests(JSON.parse(message.body)));
        stompClient.subscribe('/topic/usersResult', (message) => showResult(JSON.parse(message.body)));
    });
}

const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMsgRegister() {
    stompClient.send("/app/chat.addUser", {}, JSON.stringify({
        id: $("#userId").val(),
        login: $("#userLogin").val(),
        password: $("#userPassword").val()
    }));
}

function sendMsgLogin() {
    stompClient.send("/app/chat.checkUser", {}, JSON.stringify({
        id: $("#userId").val(),
        login: $("#userLogin").val(),
        password: $("#userPassword").val()
    }));
}

function sendMsgAll() {
    stompClient.send("/app/chat.getAll", {}, {});

}

const showMessage = (user) => {
    if ((user.id === 0) ||
        (user.login === "") ||
        (user.password === "")) {
        $("#usersStr").append();
    } else {
        $("#usersStr").append("<tr>" +
            "<td>" + user.id + "</td>" +
            "<td>" + user.login + "</td>" +
            "<td>" + user.password + "</td>" +
            "</tr>");
        $("#userLogin")[0].disabled = true;
        $("#sendLogin")[0].disabled = true;
        $("#sendRegister")[0].disabled = true;
        stompClient.send("/app/chat.getAllQuestions", {}, {});
    }
    if (user.id === 1) {
        setTimeout(() => {
            location.href = "testing.html";
        }, 2000);
        stompClient.disconnect();
    }
}

const showAll = (users) => {
    if (users.length > 0) {
        for (let i = 0; i < users.length; i++) {
            let user = users[i];
            $("#usersStr").append("<tr>" +
                "<td>" + user.id + "</td>" +
                "<td>" + user.login + "</td>" +
                "<td>" + user.password + "</td>" +
                "</tr>")
        }
    }
}

const showTests = (questions) => {
    for (let i = 0; i < questions.length; i++) {
        let question = questions[i];
        $("#testsStr").append(
            '<tr><td><label name="question_id">' + question.question_id + '</label></td><td><label name="theme">' + question.theme + '</label></td>' +

            '<tr> <td></td><td><label name="answer1">' + question.answer1 + '</label></td>' + '<td><input name="checkbox1" type="checkbox" id="checkbox1"></td> </tr>' +
            '<tr> <td></td><td><label name="answer2">' + question.answer2 + '</label></td>' + '<td><input name="checkbox2" type="checkbox" id="checkbox2"></td> </tr>' +
            '<tr> <td></td><td><label name="answer3">' + question.answer3 + '</label></td>' + '<td><input name="checkbox3" type="checkbox" id="checkbox3"></td> </tr>' +
            '<tr> <td></td><td><label name="answer4">' + question.answer4 + '</label></td>' + '<td><input name="checkbox4" type="checkbox" id="checkbox4"></td> </tr>'
        )
    }
    $("#testsStr").append(
        '<td></td><td><button type="button" id="sendAnswer" onclick="sendMsgAnswer()">Send answers</button></td>'
    )
}

function sendMsgAnswer(user, questions) {
    let table = document.getElementById('testsStr');
    let arrayOfTrValues = [];
    let count = table.rows.length;
    obj = {};
    for (let i = 0; i < (count - 1); i = i + 5) {
        obj = {};

        obj [table.rows[i].cells[0].firstChild.getAttribute('name')] = Number.parseInt(table.rows[i].cells[0].textContent);
        obj [table.rows[i].cells[1].firstChild.getAttribute('name')] = table.rows[i].cells[1].firstChild.textContent;

        obj [table.rows[i + 1].cells[1].firstChild.getAttribute('name')] = table.rows[i + 1].cells[1].textContent;
        obj [table.rows[i + 1].cells[2].firstChild.getAttribute('name')] = $(table.rows[i + 1].cells[2].firstChild).is(':checked');

        obj [table.rows[i + 2].cells[1].firstChild.getAttribute('name')] = table.rows[i + 2].cells[1].textContent;
        obj [table.rows[i + 2].cells[2].firstChild.getAttribute('name')] = $(table.rows[i + 2].cells[2].firstChild).is(':checked');

        obj [table.rows[i + 3].cells[1].firstChild.getAttribute('name')] = table.rows[i + 3].cells[1].textContent;
        obj [table.rows[i + 3].cells[2].firstChild.getAttribute('name')] = $(table.rows[i + 3].cells[2].firstChild).is(':checked');

        obj [table.rows[i + 4].cells[1].firstChild.getAttribute('name')] = table.rows[i + 4].cells[1].textContent;
        obj [table.rows[i + 4].cells[2].firstChild.getAttribute('name')] = $(table.rows[i + 4].cells[2].firstChild).is(':checked');

        arrayOfTrValues.push(obj);
    }
    stompClient.send("/app/chat.addUserAnswer", {}, JSON.stringify({
        login: $("#userLogin").val(),
        password: $("#userPassword").val(),
        answer: arrayOfTrValues
    }));
    $("#sendAnswer")[0].disabled = true;
}

const showResult = (result) => {
    $("#resultsStr").append(
        '<tr> <td><label>Number of all questions</label></td>' + '<td><label>' + result[0] + '</label></td> </tr>' +
        '<tr> <td><label>Number of actual questions</label></td>' + '<td><label>' + result[1] + '</label></td> </tr>' +
        '<tr> <td><label>Number of right answers</label></td>' + '<td><label>' + result[2] + '</label></td> </tr>' +
        '<tr> <td><label>Percentage ratio right answers/all questions</label></td>' + '<td><label>' + result[3] + '</label></td> </tr>'
    );
}