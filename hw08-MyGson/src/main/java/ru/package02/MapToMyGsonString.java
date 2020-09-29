package ru.package02;

import java.util.Map;

public class MapToMyGsonString implements ToMyGsonStringInterface {
    @Override
    public String ToMyGsonString(Object anyObject) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("{");
        var arrayPrimitive1 = (Map) anyObject;
        for (int j = 0; j < arrayPrimitive1.size(); j++) {
            if (j != 0) {
                jsonString.append(",");
            }
            if (arrayPrimitive1.isEmpty()) {
                jsonString.append("null");
            } else if ((arrayPrimitive1.values().toArray()[j] instanceof String) | (arrayPrimitive1.values().toArray()[j] instanceof Character)) {
                jsonString.append("\"" + arrayPrimitive1.keySet().toArray()[j] + "\":\"" + arrayPrimitive1.values().toArray()[j] + "\"");
            } else {
                jsonString.append("\"" + arrayPrimitive1.keySet().toArray()[j] + "\":" + arrayPrimitive1.values().toArray()[j]);
            }
        }
        jsonString.append("}");
        return jsonString.toString();
    }
}
