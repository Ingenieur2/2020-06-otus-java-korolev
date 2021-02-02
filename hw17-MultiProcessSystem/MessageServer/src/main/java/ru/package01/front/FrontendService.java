package ru.package01.front;

import ru.package01.dto.UserData;
import ru.package01.client.MessageCallback;

public interface FrontendService {
    void getUserData(long userId, MessageCallback<UserData> dataConsumer);
}

