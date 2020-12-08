package ru.package02.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.package02.dto.UserData;
import ru.package01.client.CallbackRegistry;
import ru.package01.client.MessageCallback;
import ru.package01.client.ResultDataType;
import ru.package01.message.MessageHelper;
import ru.package01.message.Message;
import ru.package01.RequestHandler;

import java.util.Optional;

public class GetUserDataResponseHandler implements RequestHandler<UserData> {
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
