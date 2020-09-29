package ru.package01;

import com.google.gson.Gson;

public class DemoMyGson {
    public static void main(String[] args) throws IllegalAccessException {
        Gson gson = new Gson();
        AnyObject obj1 = new AnyObject(22, "test342", 10, (byte) 3);
        String json = gson.toJson(obj1);

        System.out.println(json);

        MyGson myGson = new MyGson();
        AnyObject obj2 = new AnyObject(22, "test342", 10, (byte) 3);
        String myJson = myGson.toJson(obj2);
        System.out.println(myJson);

        System.out.println("Is MyGson equals standart Gson? " + myJson.equals(json));

    }
}
