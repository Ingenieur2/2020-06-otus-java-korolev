package ru.package02;

public class ArrayStringToMyGsonString implements ToMyGsonStringInterface {
    @Override
    public String ToMyGsonString(Object anyObject) {
        var arrayPrimitive1 = (String[]) anyObject;
        StringBuilder jsonString = new StringBuilder();
        for (int j = 0; j < arrayPrimitive1.length; j++) {
            if (j != 0) {
                jsonString.append(",");
            }
            if (arrayPrimitive1[j] == null) {
                jsonString.append("null");
            } else {
                jsonString.append("\"" + arrayPrimitive1[j] + "\"");
            }
        }
        return jsonString.toString();
    }
}
