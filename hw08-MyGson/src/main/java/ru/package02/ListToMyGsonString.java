package ru.package02;

import java.util.List;

public class ListToMyGsonString implements ToMyGsonStringInterface {
    @Override
    public String ToMyGsonString(Object anyObject) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("[");
        var arrayPrimitive1 = (List) anyObject;
        for (int j = 0; j < arrayPrimitive1.size(); j++) {
            if (j != 0) {
                jsonString.append(",");
            }
            if (arrayPrimitive1.get(j) == null) {
                jsonString.append("null");
            } else if ((arrayPrimitive1.get(j) instanceof String) | (arrayPrimitive1.get(j) instanceof Character)) {
                jsonString.append("\"" + arrayPrimitive1.get(j) + "\"");
            } else {
                jsonString.append(arrayPrimitive1.get(j));
            }
        }
        jsonString.append("]");
        return jsonString.toString();
    }
}
