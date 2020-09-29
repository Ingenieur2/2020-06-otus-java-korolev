package ru.package02;

public class ArrayCharToMyGsonString implements ToMyGsonStringInterface {
    @Override
    public String ToMyGsonString(Object anyObject) {
        var arrayPrimitive1 = (char[]) anyObject;
        StringBuilder jsonString = new StringBuilder();
        for (int j = 0; j < arrayPrimitive1.length; j++) {
            if (j != 0) {
                jsonString.append(",");
            }
            if (arrayPrimitive1[j] == '\u0000') {
                jsonString.append("\"\\" + "u0000\"");
            } else {
                jsonString.append("\"" + arrayPrimitive1[j] + "\"");
            }
        }
        return jsonString.toString();
    }
}
