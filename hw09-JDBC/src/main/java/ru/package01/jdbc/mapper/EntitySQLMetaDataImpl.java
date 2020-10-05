package ru.package01.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {
    private final EntityClassMetaData<T> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        return String.format("select * from %s",
                entityClassMetaData.getName());
    }

    @Override
    public String getSelectByIdSql() {
        return String.format("select * from %s where %s = ?",
                entityClassMetaData.getName(), entityClassMetaData.getIdField().getName());
    }

    @Override
    public String getInsertSql() {
        var fieldsWithoutId = entityClassMetaData.getFieldsWithoutId();
        var fieldsTemplate = fieldsWithoutId.stream()
                .map(Field::getName)
                .collect(Collectors.joining(", "));

        var valuesTemplate = "?" + ", ?".repeat(fieldsWithoutId.size() - 1);

        return String.format("insert into %s (%s) values (%s)",
                entityClassMetaData.getName(), fieldsTemplate, valuesTemplate);
    }

    @Override
    public String getUpdateSql() {
        var fieldsTemplate = entityClassMetaData.getFieldsWithoutId().stream()
                .map(field -> field.getName() + " = ?")
                .collect(Collectors.joining(", "));

        return String.format("update %s set %s where %s = ?",
                entityClassMetaData.getName(), fieldsTemplate, entityClassMetaData.getIdField().getName());
    }
}
