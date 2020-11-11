package ru.package01.processor.homework;

import ru.package01.Message;
import ru.package01.processor.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class ProcessorThrow implements Processor, DateTimeProvider {
    private final Processor processor;
    private static final Logger logger = LoggerFactory.getLogger(ProcessorThrow.class);

    public ProcessorThrow(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Message process(Message message) {
        var begin = LocalDateTime.now();
        if (begin.getSecond() % 2 == 0) {
            logger.info("Exception {}", begin.now());
            throw new RuntimeException("Runtime exception.. " + begin);
        }

        return processor.process(message);
    }

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
