package ru.package01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.package01.client.CallbackRegistry;
import ru.package01.client.CallbackRegistryImpl;
import ru.package01.client.MsClient;
import ru.package01.client.MsClientImpl;
import ru.package01.db.DbServiceImpl;
import ru.package01.db.handlers.GetUserDataRequestHandler;
import ru.package01.front.FrontendService;
import ru.package01.front.FrontendServiceImpl;
import ru.package01.front.handlers.GetUserDataResponseHandler;
import ru.package01.message.MessageType;


public class MessageAppl {
    private static final Logger logger = LoggerFactory.getLogger(MessageAppl.class);

    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    public MessageAppl(String dataFromFrontend) throws InterruptedException {
        MessageSystem messageSystem = new MessageSystemImpl();
        CallbackRegistry callbackRegistry = new CallbackRegistryImpl();

        HandlersStore requestHandlerDatabaseStore = new HandlersStoreImpl();
        requestHandlerDatabaseStore.addHandler(MessageType.USER_DATA, new GetUserDataRequestHandler(new DbServiceImpl(dataFromFrontend)));
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerDatabaseStore, callbackRegistry);
        messageSystem.addClient(databaseMsClient);
        HandlersStore requestHandlerFrontendStore = new HandlersStoreImpl();
        requestHandlerFrontendStore.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(callbackRegistry));

        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerFrontendStore, callbackRegistry);
        FrontendService frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
        messageSystem.addClient(frontendMsClient);

        frontendService.getUserData(1, data -> logger.info("got data:{}", data));

        Thread.sleep(100);
        messageSystem.dispose();
        logger.info("done");
    }
}
