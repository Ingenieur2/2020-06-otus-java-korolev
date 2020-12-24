package ru.package01.messagesystem;


import ru.package01.messagesystem.client.ResultDataType;
import ru.package01.messagesystem.message.Message;

import java.util.Optional;


public interface RequestHandler<T extends ResultDataType> {
    Optional<Message> handle(Message msg);
}
