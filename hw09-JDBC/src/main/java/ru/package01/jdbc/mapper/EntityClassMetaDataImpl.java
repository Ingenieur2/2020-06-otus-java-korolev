package ru.package01.jdbc.mapper;

import ru.package01.core.annotation.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private final String name;

    private final Constructor<T> constructor;
    private final Field idField;
    private final List<Field> allFieldList;
    private final List<Field> FieldListWithoutId;


    public EntityClassMetaDataImpl(Class<T> classString) {
        name = classString.getSimpleName();

        allFieldList = Arrays.asList(classString.getDeclaredFields());
        idField = getAnnotatedIdField(allFieldList);

        FieldListWithoutId = Arrays.stream(classString.getDeclaredFields()).filter(x -> !x.equals(idField)).collect(Collectors.toList());

        try {
            constructor = classString.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        return allFieldList;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return FieldListWithoutId;
    }

    private Field getAnnotatedIdField(List<Field> fields) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).isAnnotationPresent(Id.class)) {
                return fields.get(i);
            }
        }
        throw new RuntimeException("Тhere is no field with annotation @Id");
    }
}
