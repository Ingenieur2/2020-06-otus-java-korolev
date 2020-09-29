package ru.package02;

public class DefaultObjectToMyGsonString implements ToMyGsonStringInterface {
    @Override
    public String ToMyGsonString(Object anyObject) {
        StringBuilder jsonString = new StringBuilder();
        if ((anyObject instanceof String) | (anyObject instanceof Character)) {
            jsonString.append("\"" + anyObject + "\"");
        } else {
            jsonString.append(anyObject);
        }
        return jsonString.toString();
    }
}
