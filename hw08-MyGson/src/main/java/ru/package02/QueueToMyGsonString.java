package ru.package02;

import java.util.Queue;

public class QueueToMyGsonString implements ToMyGsonStringInterface {
    @Override
    public String ToMyGsonString(Object anyObject) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("[");
        var arrayPrimitive1 = (Queue) anyObject;
        for (int j = 0; j < arrayPrimitive1.size(); j++) {
            if (j != 0) {
                jsonString.append(",");
            }
            if (arrayPrimitive1.toArray()[j] == null) {
                jsonString.append("null");
            } else if ((arrayPrimitive1.toArray()[j] instanceof String) | (arrayPrimitive1.toArray()[j] instanceof Character)) {
                jsonString.append("\"" + arrayPrimitive1.toArray()[j] + "\"");
            } else {
                jsonString.append(arrayPrimitive1.toArray()[j]);
            }
        }
        jsonString.append("]");
        return jsonString.toString();
    }
}
