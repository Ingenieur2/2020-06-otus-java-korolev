package ru.package01;

import ru.package02.ConfigurationFactory;
import ru.package02.ToMyGsonStringInterface;

import java.lang.reflect.Field;

public class MyGson {
    MyGson() {

    }

    String toJson(Object AnyObject) throws IllegalAccessException {
        Class<AnyObject> classString = AnyObject.class;

        Field[] fields = classString.getDeclaredFields();
        StringBuilder jsonString = new StringBuilder();
        if (fields.length != 0) {
            jsonString.append("{\"");
        } else {
            jsonString.append("{");
        }

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].get(AnyObject) != null) {
                if (i != 0) {
                    jsonString.append(",\"");
                }
                jsonString.append(fields[i].getName() + "\":");
                if (fields[i].getType().isArray()) {
                    jsonString.append("[");
                }

                ToMyGsonStringInterface config = ConfigurationFactory.getConfiguration(fields[i].getType().toString());
                jsonString.append(config.ToMyGsonString(fields[i].get(AnyObject)));

                if (fields[i].getType().isArray()) {
                    jsonString.append("]");
                }
            }
        }
        jsonString.append("}");
        return jsonString.toString();
    }
}