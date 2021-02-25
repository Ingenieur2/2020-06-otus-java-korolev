let stompClient = null;

const setConnected = (connected) => {
    $('#connect').prop("disabled", connected);
    $('#disconnect').prop("disabled", !connected);
    if (connected) {
        $('#usersList').show();
        $('#tests').show();
        $('#questions').show();
        $('#results').show();

    } else {
        $('#usersList').hide();
        $('#tests').hide();
        $('#questions').hide();
        $('#results').hide();
        $('#create-form')[0].reset();
        $('#userLogin')[0].disabled = false;
        $('#sendLogin')[0].disabled = false;
        $('#sendRegister')[0].disabled = false;
    }
    $('#usersTable > tbody').empty();
    $('#testsTable > tbody').empty();
    $('#questions').empty();
    $('#resultsStr').empty();
    sendMsgAll();
}

const connect = () => {
    stompClient = Stomp.over(new SockJS('/websocket'));
    stompClient.connect({}, (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/getAllUsers', (message) => showAllUsers(JSON.parse(message.body)));
        stompClient.subscribe('/topic/users', (message) => showMessage(JSON.parse(message.body)));
        stompClient.subscribe('/topic/getAllQuestions', (message) => showQuestions(JSON.parse(message.body)));
        stompClient.subscribe('/topic/getAllAnswers', (message) => showAnswers(JSON.parse(message.body)));
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
        id: $('#userId').val(),
        login: $('#userLogin').val(),
        password: $('#userPassword').val()
    }));
}

function sendMsgLogin() {
    stompClient.send("/app/chat.checkUser", {}, JSON.stringify({
        id: $('#userId').val(),
        login: $('#userLogin').val(),
        password: $('#userPassword').val()
    }));
}

function sendMsgAll() {
    stompClient.send("/app/chat.getAllUsers", {}, {});
}

const showMessage = (user) => {
    if ((user.id === 0) ||
        (user.login === "") ||
        (user.password === "")) {
        $('#usersStr').append();
    } else {
        $('#usersStr').append('<tr>' +
            '<td > <label id="user_id0">' + user.id + '</td>' +
            '<td>' + user.login + '</td>' +
            '<td>' + user.password + '</td>' +
            '</tr>');
        $('#userLogin')[0].disabled = true;
        $('#sendLogin')[0].disabled = true;
        $('#sendRegister')[0].disabled = true;
        stompClient.send("/app/chat.getAllQuestions", {}, {});
        stompClient.send("/app/chat.getAllAnswers", {}, {});
    }
    if (user.id === 1) {
        setTimeout(() => {
            location.href = "testing.html";
        }, 2000);
        stompClient.disconnect();
    }
}

const showAllUsers = (users) => {
    if (users.length > 0) {
        for (let i = 0; i < users.length; i++) {
            let user = users[i];
            $('#usersStr').append('<tr>' +
                '<td >' + user.id + '</td>' +
                '<td>' + user.login + '</td>' +
                '<td>' + user.password + '</td>' +
                '</tr>')
        }
    }
}

const showAnswers = (answers) => {
    let maxValue = 0;
    for (let i = 0; i < answers.length; i++) {
        if (maxValue < answers[i].question_id) {
            maxValue = answers[i].question_id;
        }
    }

    for (let i = 0; i < maxValue; i++) {
        let k = 1;
        for (let j = 0; j < answers.length; j++) {
            if (i === answers[j].question_id - 1) {
                $('#answers_' + answers[j].question_id).append(
                    '<tr>   <td>    <label id="answer_id' + answers[j].id + '">' + answers[j].id + ' </label>      </td>' +
                    '       <td>    <label id="answer_' + answers[j].id + '">' + answers[j].answer + ' </label>    </td>' +
                    '       <td>    <input name="checkbox" type="checkbox" id="checkbox' + answers[j].question_id + '' + k + '">      </td></tr>'
                );
                k++;
            }
        }
    }
}

const showQuestions = (questions) => {
    for (let i = 0; i < questions.length; i++) {
        let question = questions[i];
        $('#questions').append(
            '<table id="question_' + question.id + '" style="width: 500px; border: solid 1px;">');
        $('#question_' + question.id).append(
            '<thead>' +
            '<tr><td style="width: 50px"   ><label id="question_id' + question.id + '">' + question.id + '</label></td><td style="width: 350px"><label name="theme">' + question.question + '</label></td><td style="width: 100px"></td></tr>' +
            '</thead>' +
            '<tbody id="answers_' + question.id + '">' +
            '</tbody>' +
            '</table>'
        );
    }
    $('#usersList').hide();
    $('#questions').append(
        '<td></td><td><button type="button" id="sendAnswer" onclick="sendMsgAnswer()">Send answers</button></td>'
    )
}

function sendMsgAnswer(user, questions, answers) {
    let table = document.getElementById('questions');
    let arrayOfTrValues = [];
    let obj = {};
    let arrayOfQuestions = table.childNodes;
    for (let i = 1; i < arrayOfQuestions.length - 1; i++) {

        let arrayOfAnswers = document.getElementById('answers_' + i).childNodes;
        for (let j = 0; j < arrayOfAnswers.length; j++) {
            obj["id"] = 0;
            obj ["user_id"] = Number.parseInt(document.getElementById('user_id0').textContent);
            obj ["question_id"] = Number.parseInt(document.getElementById('question_id' + i).textContent);
            obj ["answer_id"] = Number.parseInt(arrayOfAnswers[j].cells[0].textContent);
            obj ["checkbox"] = $('#checkbox' + i + '' + (j + 1)).is(':checked');
            arrayOfTrValues.push(obj);
            obj = {};
        }
    }
    stompClient.send("/app/chat.checkUserAnswer", {}, JSON.stringify(arrayOfTrValues));
    stompClient.send("/app/chat.addUserAnswer", {}, JSON.stringify(arrayOfTrValues));
    $('#sendAnswer')[0].disabled = true;
}

const showResult = (result) => {
    $('#resultsStr').append(
        '<tr> <td><label>Number of all questions</label></td>' + '<td><label>' + result[0] + '</label></td> </tr>' +
        '<tr> <td><label>Number of right answers</label></td>' + '<td><label>' + result[1] + '</label></td> </tr>' +
        '<tr> <td><label>Percentage ratio right answers/all questions</label></td>' + '<td><label>' + result[2] + '</label></td> </tr>'
    );
}