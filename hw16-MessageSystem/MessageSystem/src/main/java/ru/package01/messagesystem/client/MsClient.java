package ru.package01.messagesystem.client;

import ru.package01.messagesystem.message.Message;
import ru.package01.messagesystem.message.MessageType;

public interface MsClient {

    boolean sendMessage(Message msg);

    void handle(Message msg);

    String getName();

    <T extends ResultDataType> Message produceMessage(String to, T data,
                                                      MessageType msgType, MessageCallback<T> callback);
}
