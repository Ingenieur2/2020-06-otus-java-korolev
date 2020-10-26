package ru.package01.mycache;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HWCacheDemo {
    private static final Logger logger = LoggerFactory.getLogger(HWCacheDemo.class);

    public static void main(String[] args) throws InterruptedException {
        new HWCacheDemo().demo();
    }

    private void demo() throws InterruptedException {
        HwCache<String, String> cache = new MyCache<>();

        cache.addListener();
        Gson gson = new Gson();
        for (int i = 1; i <= 15; i++) {
            cache.put(String.valueOf(i), String.valueOf(i * i));
        }
        String json = gson.toJson(cache);
        System.out.println("current state is:  " + json);

        logger.info("getValue:{}", cache.get(String.valueOf(3)));

        cache.remove(String.valueOf(11));
        cache.remove(String.valueOf(13));
        json = gson.toJson(cache);
        System.out.println("state after deleting elements is:  " + json);

        cache.removeListener();
        System.out.print("clearing cache.......");
        json = gson.toJson(cache);
        System.out.println("current state is:  " + json);

        cache.addListener();
        for (int i = 20; i <= 21; i++) {
            cache.put(String.valueOf(i), String.valueOf(i * i));
        }
        json = gson.toJson(cache);
        System.out.println("NEW state is:  " + json);
    }
}
