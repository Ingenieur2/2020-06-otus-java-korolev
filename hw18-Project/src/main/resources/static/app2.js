let stompClient = null;

const setConnected = (connected) => {
    $('#connect').prop("disabled", connected);
    $('#disconnect').prop("disabled", !connected);
    if (connected) {
        $('#questionsList').show();
        $('#answersList').show();
    } else {
        $('#questionsList').hide();
        $('#answersList').hide();
    }
    $('#questionsTable > tbody').empty();
    $('#answersTable > tbody').empty();
    sendMsgAllQuestions();
    sendMsgAllAnswers();
}

const connect = () => {
    stompClient = Stomp.over(new SockJS('/websocket'));
    stompClient.connect({}, (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/getAllQuestions', (message) => showAllQuestions(JSON.parse(message.body)));
        stompClient.subscribe('/topic/questions', (message) => showMessageQuestion(JSON.parse(message.body)));
        stompClient.subscribe('/topic/getAllAnswers', (message) => showAllAnswers(JSON.parse(message.body)));
        stompClient.subscribe('/topic/answers', (message) => showMessageAnswer(JSON.parse(message.body)));
    });
}

const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMsgRegister2() {
    stompClient.send("/app/chat.addQuestion", {}, JSON.stringify({
        question_id: $('#questionId').val(),
        question: $('#question').val()
    }));
    $('#create-form')[0].reset();
}

function sendMsgRegister3() {
    stompClient.send("/app/chat.addAnswer", {}, JSON.stringify({
        answer: $('#answer').val(),
        question_id: $('#question_id').val(),
        checkbox: $('#checkbox').is(':checked')
    }));
    $('#create-form3')[0].reset();
}

function sendMsgAllQuestions() {
    stompClient.send("/app/chat.getAllQuestions", {}, {});
}

function sendMsgAllAnswers() {
    stompClient.send("/app/chat.getAllAnswers", {}, {});
}

const showMessageQuestion = (question) => {
    if ((question.id === 0) ||
        (question.question === "")
    ) {
        $('#questionsStr').append();
    } else {

        $('#questionsStr').append('<tr>' +
            '<td>' + question.id + '</td>' +
            '<td>' + question.question + '</td>' +
            '</tr>');
    }
}

const showMessageAnswer = (right_answer) => {
    if ((right_answer.id === 0) ||
        (right_answer.answer === "")
    ) {
        $("#answersStr").append();
    } else {
    $('#answersStr').append('<tr>' +
        '<td>' + right_answer.id + '</td>' +
        '<td>' + right_answer.answer + '</td>' +
        '<td>' + right_answer.question_id + '</td>' +
        '<td>' + right_answer.checkbox + '</td>' +
        '</tr>');
    }
}

const showAllQuestions = (questions) => {
    if (questions.length > 0) {
        for (let i = 0; i < questions.length; i++) {
            let question = questions[i];
            $('#questionsStr').append('<tr>' +
                '<td>' + question.id + '</td>' +
                '<td>' + question.question + '</td>' +
                '</tr>')
        }
    }
}

const showAllAnswers = (right_answers) => {
    if (right_answers.length > 0) {
        for (let i = 0; i < right_answers.length; i++) {
            let right_answer = right_answers[i];
            $('#answersStr').append('<tr>' +
                '<td>' + right_answer.id + '</td>' +
                '<td>' + right_answer.answer + '</td>' +
                '<td>' + right_answer.question_id + '</td>' +
                '<td>' + right_answer.checkbox + '</td>' +
                '</tr>')
        }
    }
}
