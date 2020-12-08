package ru.package01.client;

import java.util.function.Consumer;

public interface MessageCallback<T extends ResultDataType> extends Consumer<T> {
}
