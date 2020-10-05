package ru.package01.jdbc.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

/**
 * "Parsing" object into component parts
 * @param <T>
 */
public interface EntityClassMetaData<T> {
    String getName();

    Constructor<T> getConstructor();

    Field getIdField();

    List<Field> getAllFields();

    List<Field> getFieldsWithoutId();
}
