package ru.package02;

public class ConfigurationFactory {
    public static ToMyGsonStringInterface getConfiguration(String param) {
        switch (param) {
            case "class [B": {
                return new ArrayByteToMyGsonString();
            }
            case "class [S": {
                return new ArrayShortToMyGsonString();
            }
            case "class [I": {
                return new ArrayIntegerToMyGsonString();
            }
            case "class [J": {
                return new ArrayLongToMyGsonString();
            }
            case "class [F": {
                return new ArrayFloatToMyGsonString();
            }
            case "class [D": {
                return new ArrayDoubleToMyGsonString();
            }
            case "class [C": {
                return new ArrayCharToMyGsonString();
            }
            case "class [Ljava.lang.String;": {
                return new ArrayStringToMyGsonString();
            }
            case "interface java.util.Deque": {
                return new DequeToMyGsonString();
            }
            case "interface java.util.List": {
                return new ListToMyGsonString();
            }
            case "interface java.util.Map": {
                return new MapToMyGsonString();
            }
            case "interface java.util.Queue": {
                return new QueueToMyGsonString();
            }
            case "interface java.util.Set": {
                return new SetToMyGsonString();
            }
            case "class java.lang.String;": {
                return new StringToMyGsonString();
            }
            default:
                return new DefaultObjectToMyGsonString();
        }

    }
}
