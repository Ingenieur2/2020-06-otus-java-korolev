package ru.package01.handler;

import ru.package01.Message;
import ru.package01.listener.Listener;

public interface Handler {
    Message handle(Message msg);

    void addListener(Listener listener);

    void removeListener(Listener listener);
}
