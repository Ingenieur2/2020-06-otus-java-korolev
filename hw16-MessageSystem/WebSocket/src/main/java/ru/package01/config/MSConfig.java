package ru.package01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.package01.services.FrontendService;
import ru.package01.services.FrontendServiceImpl;
import ru.package01.services.handlers.GetUserDataRequestHandler;
import ru.package01.services.handlers.GetUserDataResponseHandler;
import ru.package01.messagesystem.HandlersStore;
import ru.package01.messagesystem.HandlersStoreImpl;
import ru.package01.messagesystem.MessageSystem;
import ru.package01.messagesystem.MessageSystemImpl;
import ru.package01.messagesystem.client.CallbackRegistry;
import ru.package01.messagesystem.client.CallbackRegistryImpl;
import ru.package01.messagesystem.client.MsClient;
import ru.package01.messagesystem.client.MsClientImpl;
import ru.package01.messagesystem.message.MessageType;

@Configuration
public class MSConfig {
    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    @Bean(destroyMethod = "dispose")
    public MessageSystem messageSystem() {
        return new MessageSystemImpl();
    }

    @Bean
    public CallbackRegistry callbackRegistry() {
        return new CallbackRegistryImpl();
    }

    @Bean(name = "databaseMsClient")
    public MsClient databaseMsClient(MessageSystem messageSystem, CallbackRegistry callbackRegistry,
                                     GetUserDataRequestHandler requestHandler) {
        HandlersStore requestHandlerDatabaseStore = new HandlersStoreImpl();
        requestHandlerDatabaseStore.addHandler(MessageType.USER_DATA, requestHandler);
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerDatabaseStore, callbackRegistry);
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean(name = "frontendMsClient")
    public MsClient frontendMsClient(MessageSystem messageSystem, CallbackRegistry callbackRegistry,
                                     GetUserDataResponseHandler responseHandler) {
        HandlersStore responseHandlerFrontendStore = new HandlersStoreImpl();
        responseHandlerFrontendStore.addHandler(MessageType.USER_DATA, responseHandler);

        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME,
                messageSystem, responseHandlerFrontendStore, callbackRegistry);
        messageSystem.addClient(frontendMsClient);

        return frontendMsClient;
    }

    @Bean
    public FrontendService frontendService(MsClient frontendMsClient) {
        return new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
    }
}
