package ru.package01.services;

import ru.package01.core.model.User;
import ru.package01.messagesystem.client.MessageCallback;
import ru.package01.messagesystem.client.MsClient;
import ru.package01.messagesystem.message.Message;
import ru.package01.messagesystem.message.MessageType;

public class FrontendServiceImpl implements FrontendService {
    private final MsClient msClient;
    private final String databaseServiceClientName;

    public FrontendServiceImpl(MsClient msClient, String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public void saveUser(User user, MessageCallback<User> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, user,
                MessageType.USER_DATA, dataConsumer);
        msClient.sendMessage(outMsg);
    }
}
