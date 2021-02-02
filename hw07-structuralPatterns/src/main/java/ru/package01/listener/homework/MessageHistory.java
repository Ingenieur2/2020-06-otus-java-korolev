package ru.package01.listener.homework;

import ru.package01.Message;

public class MessageHistory {

    private final Message oldMessage;
    private final Message newMessage;

    public MessageHistory(Message oldMessage, Message newMessage) throws CloneNotSupportedException {
        this.oldMessage = (Message) oldMessage.clone();
        this.newMessage = (Message) newMessage.clone();
    }

    public Message getOldMessage() {
        return oldMessage;
    }

    public Message getNewMessage() {
        return newMessage;
    }

    @Override
    public String toString() {
        return String.format("oldMsg: %s, newMsg: %s", oldMessage, newMessage);
    }
}
