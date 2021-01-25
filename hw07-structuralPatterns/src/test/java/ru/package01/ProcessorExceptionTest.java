package ru.package01;

import org.junit.jupiter.api.*;
import ru.package01.handler.ComplexProcessor;
import ru.package01.processor.Processor;
import ru.package01.processor.homework.ProcessorThrow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

class ProcessorExceptionTest {
    private static final Logger logger = LoggerFactory.getLogger(ProcessorExceptionTest.class);

    @BeforeAll
    public static void globalSetUp() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    public void setUp() {
        System.out.print("\n@BeforeEach. ");
        System.out.println("Экземпляр тестового класса: " + Integer.toHexString(hashCode()));
    }

    @Test
    @DisplayName("Testing exception: ")
    void handleExceptionTest() throws InterruptedException {
        List processors = new ArrayList<>();

        var message1 = new Message.Builder().field1("field1").build();
        var processor1 = mock(Processor.class);
        var processorThrow = new ProcessorThrow(processor1);

        processors.add(processorThrow);
        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
            throw new TestException(ex.getMessage());
        });

        for (int i = 1; i < 2; i++) { //if i = 2 -> fail 50-50
            processorThrow.process(message1);
            logger.info("Write message: {}", LocalDateTime.now());
            Thread.sleep(1000);
        }
    }

    @AfterEach
    public void tearDown() {
        System.out.print("@AfterEach. ");
        System.out.println("Экземпляр тестового класса: " + Integer.toHexString(hashCode()));
    }

    @AfterAll
    public static void globalTearDown() {
        System.out.println("\n@AfterAll");
    }

    private static class TestException extends RuntimeException {
        public TestException(String message) {
            super(message);
        }
    }
}
