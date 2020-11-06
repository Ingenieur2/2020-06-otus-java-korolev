package ru.package01.listener.homework;

import ru.package01.Message;
import ru.package01.listener.Listener;

import java.util.ArrayList;
import java.util.List;

public class ListenerImpl implements Listener {
    private final List<MessageHistory> historyList = new ArrayList<>();

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        try {
            historyList.add(new MessageHistory(oldMsg, newMsg));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public List<MessageHistory> getHistoryList() {
        return historyList;
    }
}
