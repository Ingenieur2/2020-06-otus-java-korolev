package ru.package01.processor.homework;

import ru.package01.Message;
import ru.package01.processor.Processor;

public class ProcessorSwap11and12 implements Processor {
    @Override
    public Message process(Message message) {
        return message.toBuilder().field11(message.getField12()).field12(message.getField11()).build();
    }
}
