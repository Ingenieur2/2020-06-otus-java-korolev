package ru.package01.services.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.package01.core.model.User;
import ru.package01.messagesystem.RequestHandler;
import ru.package01.messagesystem.client.CallbackRegistry;
import ru.package01.messagesystem.client.MessageCallback;
import ru.package01.messagesystem.client.ResultDataType;
import ru.package01.messagesystem.message.Message;
import ru.package01.messagesystem.message.MessageHelper;

import java.util.Optional;

@Component
public class GetUserDataResponseHandler implements RequestHandler<User> {
    private static final Logger logger = LoggerFactory.getLogger(GetUserDataResponseHandler.class);
    private final CallbackRegistry callbackRegistry;

    public GetUserDataResponseHandler(CallbackRegistry callbackRegistry) {
        this.callbackRegistry = callbackRegistry;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        logger.info("new message:{}", msg);
        try {
            MessageCallback<? extends ResultDataType> callback = callbackRegistry.getAndRemove(msg.getCallbackId());
            if (callback != null) {
                callback.accept(MessageHelper.getPayload(msg));
            } else {
                logger.error("callback for Id:{} not found", msg.getCallbackId());
            }
        } catch (Exception ex) {
            logger.error("msg:{}", msg, ex);
        }
        return Optional.empty();
    }
}
