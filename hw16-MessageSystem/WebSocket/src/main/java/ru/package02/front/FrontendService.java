package ru.package02.front;

import ru.package02.dto.UserData;
import ru.package01.client.MessageCallback;

public interface FrontendService {
    void getUserData(long userId, MessageCallback<UserData> dataConsumer);
}

