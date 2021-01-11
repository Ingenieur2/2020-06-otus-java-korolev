let stompClient = null;

const setConnected = (connected) => {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#questionsList").show();
    } else {
        $("#questionsList").hide();
    }
    $("#questionsTable > tbody").empty();
    sendMsgAll();
}

const connect = () => {
    stompClient = Stomp.over(new SockJS('/websocket'));
    stompClient.connect({}, (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/getAllQuestions', (message) => showAllQuestions(JSON.parse(message.body)));
        stompClient.subscribe('/topic/questions', (message) => showMessage(JSON.parse(message.body)));
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
        question_id: $("#questionId").val(),
        theme: $("#theme").val(),
        answer1: $("#answer1").val(),
        checkbox1: $("#checkbox1").is(':checked'),
        answer2: $("#answer2").val(),
        checkbox2: $("#checkbox2").is(':checked'),
        answer3: $("#answer3").val(),
        checkbox3: $("#checkbox3").is(':checked'),
        answer4: $("#answer4").val(),
        checkbox4: $("#checkbox4").is(':checked')
    }));
    $("#create-form")[0].reset();
}

function sendMsgAll() {
    stompClient.send("/app/chat.getAllQuestions", {}, {});
}

const showMessage = (question) => {
    if ((question.question_id === 0) ||
        (question.theme === "") ||
        (question.answer1 === "") ||
        (question.answer2 === "") ||
        (question.answer3 === "") ||
        (question.answer4 === "") ||

        ((question.checkbox1 === false) &&
            (question.checkbox2 === false) &&
            (question.checkbox3 === false) &&
            (question.checkbox4 === false))
    ) {
        $("#questionsStr").append();
    } else {
        $("#questionsStr").append("<tr>" +
            "<td>" + question.question_id + "</td>" +
            "<td>" + question.theme + "</td>" +
            "<td>" + question.answer1 + "</td>" + "<td>" + question.checkbox1 + "</td>" +
            "<td>" + question.answer2 + "</td>" + "<td>" + question.checkbox2 + "</td>" +
            "<td>" + question.answer3 + "</td>" + "<td>" + question.checkbox3 + "</td>" +
            "<td>" + question.answer4 + "</td>" + "<td>" + question.checkbox4 + "</td>" +
            "</tr>");
    }
}

const showAllQuestions = (questions) => {
    if (questions.length > 0) {
        for (let i = 0; i < questions.length; i++) {
            let question = questions[i];
            $("#questionsStr").append("<tr>" +
                "<td>" + question.question_id + "</td>" +
                "<td>" + question.theme + "</td>" +
                "<td>" + question.answer1 + "</td>" + "<td>" + question.checkbox1 + "</td>" +
                "<td>" + question.answer2 + "</td>" + "<td>" + question.checkbox2 + "</td>" +
                "<td>" + question.answer3 + "</td>" + "<td>" + question.checkbox3 + "</td>" +
                "<td>" + question.answer4 + "</td>" + "<td>" + question.checkbox4 + "</td>" +
                "</tr>")
        }
    }
}