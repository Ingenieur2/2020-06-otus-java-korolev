package ru.package02;

public class StringToMyGsonString implements ToMyGsonStringInterface {
    @Override
    public String ToMyGsonString(Object anyObject) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("\"" + anyObject + "\"");
        return jsonString.toString();
    }
}
