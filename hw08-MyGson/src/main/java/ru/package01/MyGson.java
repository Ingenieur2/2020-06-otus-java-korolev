package ru.package01;

import java.lang.reflect.Field;
import java.util.*;

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

                switch (fields[i].getType().toString()) {
                    case "class [B": {
                        var arrayPrimitive1 = (byte[]) fields[i].get(AnyObject);
                        for (int j = 0; j < arrayPrimitive1.length; j++) {
                            if (j != 0) {
                                jsonString.append(",");
                            }
                            jsonString.append(arrayPrimitive1[j]);
                        }
                        break;
                    }
                    case "class [S": {
                        var arrayPrimitive1 = (short[]) fields[i].get(AnyObject);
                        for (int j = 0; j < arrayPrimitive1.length; j++) {
                            if (j != 0) {
                                jsonString.append(",");
                            }
                            jsonString.append(arrayPrimitive1[j]);
                        }
                        break;
                    }
                    case "class [I": {
                        var arrayPrimitive1 = (int[]) fields[i].get(AnyObject);
                        for (int j = 0; j < arrayPrimitive1.length; j++) {
                            if (j != 0) {
                                jsonString.append(",");
                            }
                            jsonString.append(arrayPrimitive1[j]);
                        }
                        break;
                    }
                    case "class [J": {
                        var arrayPrimitive1 = (long[]) fields[i].get(AnyObject);
                        for (int j = 0; j < arrayPrimitive1.length; j++) {
                            if (j != 0) {
                                jsonString.append(",");
                            }
                            jsonString.append(arrayPrimitive1[j]);
                        }
                        break;
                    }
                    case "class [F": {
                        var arrayPrimitive1 = (float[]) fields[i].get(AnyObject);
                        for (int j = 0; j < arrayPrimitive1.length; j++) {
                            if (j != 0) {
                                jsonString.append(",");
                            }
                            jsonString.append(arrayPrimitive1[j]);
                        }
                        break;
                    }
                    case "class [D": {
                        var arrayPrimitive1 = (double[]) fields[i].get(AnyObject);
                        for (int j = 0; j < arrayPrimitive1.length; j++) {
                            if (j != 0) {
                                jsonString.append(",");
                            }
                            jsonString.append(arrayPrimitive1[j]);
                        }
                        break;
                    }
                    case "class [C": {
                        var arrayPrimitive1 = (char[]) fields[i].get(AnyObject);
                        for (int j = 0; j < arrayPrimitive1.length; j++) {
                            if (j != 0) {
                                jsonString.append(",");
                            }
                            //  arrayChar1[j]= (char[]) null;
                            if (arrayPrimitive1[j] == '\u0000') {
                                jsonString.append("\"\\" + "u0000\"");
                            } else {
                                jsonString.append("\"" + arrayPrimitive1[j] + "\"");
                            }
                        }
                        break;
                    }
                    case "class [Ljava.lang.String;": {
                        var arrayPrimitive1 = (String[]) fields[i].get(AnyObject);
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
                        break;
                    }
                    case "interface java.util.Deque": {
                        jsonString.append("[");
                        var arrayPrimitive1 = (Deque) fields[i].get(AnyObject);
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
                        break;
                    }
                    case "interface java.util.List": {
                        jsonString.append("[");
                        var arrayPrimitive1 = (List) fields[i].get(AnyObject);
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
                        break;
                    }
                    case "interface java.util.Map": {
                        jsonString.append("{");
                        var arrayPrimitive1 = (Map) fields[i].get(AnyObject);
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
                        break;
                    }
                    case "interface java.util.Queue": {
                        jsonString.append("[");
                        var arrayPrimitive1 = (Queue) fields[i].get(AnyObject);
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
                        break;
                    }
                    case "interface java.util.Set": {
                        jsonString.append("[");
                        var arrayPrimitive1 = (Set) fields[i].get(AnyObject);
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
                        break;
                    }
                    case "class java.lang.String;": {
                        jsonString.append("\"" + fields[i].get(AnyObject) + "\"");
                    }

                    default: {
                        if ((fields[i].get(AnyObject) instanceof String) | (fields[i].get(AnyObject) instanceof Character)) {
                            jsonString.append("\"" + fields[i].get(AnyObject) + "\"");
                        } else {
                            jsonString.append(fields[i].get(AnyObject));
                        }
                        break;
                    }
                }
                if (fields[i].getType().isArray()) {
                    jsonString.append("]");
                }
            }
        }
        jsonString.append("}");
        return jsonString.toString();
    }
}