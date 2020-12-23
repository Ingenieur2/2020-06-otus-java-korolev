package ru.package01.services.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.package01.core.model.User;
import ru.package01.core.service.DbServiceUser;
import ru.package01.messagesystem.RequestHandler;
import ru.package01.messagesystem.message.Message;
import ru.package01.messagesystem.message.MessageBuilder;
import ru.package01.messagesystem.message.MessageHelper;

import java.util.List;
import java.util.Optional;

@Component
public class GetUserDataRequestHandler implements RequestHandler<User> {
    private static final Logger logger = LoggerFactory.getLogger(GetUserDataRequestHandler.class);
    private final DbServiceUser dbService;

    public GetUserDataRequestHandler(DbServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        logger.info("new message:{}", msg);
        User user = MessageHelper.getPayload(msg);
        dbService.saveUser(user);
        return Optional.of(MessageBuilder.buildReplyMessage(msg, user));
    }
}
