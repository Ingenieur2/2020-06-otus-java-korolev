package ru.package02;

public class ArrayIntegerToMyGsonString implements ToMyGsonStringInterface{
    @Override
    public String ToMyGsonString(Object anyObject) {
        var arrayPrimitive1 = (int[]) anyObject;
        StringBuilder jsonString = new StringBuilder();
        for (int j = 0; j < arrayPrimitive1.length; j++) {
            if (j != 0) {
                jsonString.append(",");
            }
            jsonString.append(arrayPrimitive1[j]);
        }
        return jsonString.toString();
    }
}
