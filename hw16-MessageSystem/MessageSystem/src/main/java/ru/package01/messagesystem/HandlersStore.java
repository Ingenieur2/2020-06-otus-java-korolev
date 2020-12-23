package ru.package01.messagesystem;

import ru.package01.messagesystem.client.ResultDataType;
import ru.package01.messagesystem.message.MessageType;

public interface HandlersStore {
    RequestHandler<? extends ResultDataType> getHandlerByType(String messageTypeName);

    void addHandler(MessageType messageType, RequestHandler<? extends ResultDataType> handler);
}
