package ru.package01;

import ru.package01.handler.ComplexProcessor;
import ru.package01.listener.homework.ListenerImpl;
import ru.package01.processor.Processor;
import ru.package01.processor.homework.ProcessorThrow;
import ru.package01.processor.homework.ProcessorSwap11and12;

import java.util.ArrayList;
import java.util.List;

public class HomeWork1 {
    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
       4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элементов "to do" создать new ComplexProcessor и обработать сообщение
         */

        List<Processor> processors = List.of(new ProcessorThrow(new ProcessorSwap11and12()));
        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
            System.out.println(ex.getMessage());
        });

        var listenerMessageHistory = new ListenerImpl();
        complexProcessor.addListener(listenerMessageHistory);

        var message = new Message.Builder()
                .field1("field1")
                .field11("field11")
                .field12("field12")
                .field13(new ObjectForMessage())
                .build();
        List<String> fieldsList = new ArrayList<>();
        fieldsList.add("123");
        fieldsList.add("text");
        message.getField13().setData(fieldsList);
        System.out.println("field13: " + message.getField13().getData());
        var result = complexProcessor.handle(message);
        System.out.println("result is: " + result);
        System.out.println("history: " + listenerMessageHistory.getHistoryList());
        complexProcessor.removeListener(listenerMessageHistory);
        System.out.println("The field13 has not changed: " + result.getField13().getData());
    }
}
