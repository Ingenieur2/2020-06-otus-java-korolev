package ru.package01.services;

import ru.package01.core.model.User;
import ru.package01.messagesystem.client.MessageCallback;

public interface FrontendService {
    void saveUser(User user, MessageCallback<User> dataConsumer);
}

