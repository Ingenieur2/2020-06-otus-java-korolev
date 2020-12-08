package ru.package02.db.handlers;

import ru.package01.RequestHandler;
import ru.package02.dto.UserData;
import ru.package01.message.MessageHelper;
import ru.package02.db.DbService;
import ru.package01.message.Message;
import ru.package01.message.MessageBuilder;

import java.util.Optional;


public class GetUserDataRequestHandler implements RequestHandler<UserData> {
    private final DbService dbService;

    public GetUserDataRequestHandler(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        UserData userData = MessageHelper.getPayload(msg);
        UserData data = new UserData(userData.getUserId(), dbService.getUserData(userData.getUserId()));
        return Optional.of(MessageBuilder.buildReplyMessage(msg, data));
    }
}
