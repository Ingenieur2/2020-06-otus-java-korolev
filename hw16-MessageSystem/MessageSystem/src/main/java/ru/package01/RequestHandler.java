package ru.package01;


import ru.package01.client.ResultDataType;
import ru.package01.message.Message;

import java.util.Optional;


public interface RequestHandler<T extends ResultDataType> {
    Optional<Message> handle(Message msg);
}
