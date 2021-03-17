package ru.package01.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.package01.core.model.Answer;
import ru.package01.core.service.DbServiceAnswer;

import java.util.List;

@Controller
public class AnswerController {
    private final SimpMessagingTemplate messagingTemplate;
    private final DbServiceAnswer dbServiceAnswer;

    public AnswerController(SimpMessagingTemplate messagingTemplate, DbServiceAnswer dbServiceAnswer) {
        this.messagingTemplate = messagingTemplate;
        this.dbServiceAnswer = dbServiceAnswer;
    }

    @MessageMapping("/chat.addAnswer")
    public void answerSave(String answerString) {
        long id = dbServiceAnswer.saveAnswer(answerString);
        if (id != 0) {
            messagingTemplate.convertAndSend("/topic/answers", dbServiceAnswer.getAnswer(id));
        }
    }

    @MessageMapping("/chat.getAllAnswers")
    public void getAll() {
        List<Answer> answers = dbServiceAnswer.getAll();
        messagingTemplate.convertAndSend("/topic/getAllAnswers", answers);
    }

    @MessageMapping("/chat.getAnswersForThisQuestion")
    public void getAnswersForThisQuestion(String question_id) {
        List<Answer> answersForThisQuestion = dbServiceAnswer.getAnswersForThisQuestion(Long.parseLong(question_id));
        messagingTemplate.convertAndSend("/topic/getAnswersForThisQuestion", answersForThisQuestion);
    }
}
