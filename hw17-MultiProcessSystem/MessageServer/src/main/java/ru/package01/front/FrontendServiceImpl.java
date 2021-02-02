package ru.package01.front;

import ru.package01.dto.UserData;
import ru.package01.client.MessageCallback;
import ru.package01.message.Message;
import ru.package01.message.MessageType;
import ru.package01.client.MsClient;


public class FrontendServiceImpl implements FrontendService {

    private final MsClient msClient;
    private final String databaseServiceClientName;

    public FrontendServiceImpl(MsClient msClient, String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public void getUserData(long userId, MessageCallback<UserData> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, new UserData(userId),
                MessageType.USER_DATA, dataConsumer);
        msClient.sendMessage(outMsg);
    }
}
