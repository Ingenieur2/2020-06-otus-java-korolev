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
        HwCache<Integer, Integer> cache = new MyCache<>();

        // пример, когда Idea предлагает упростить код, при этом может появиться "спец"-эффект
        HwListener<Integer, Integer> listener = new HwListener<Integer, Integer>() {
            @Override
            public void notify(Integer key, Integer value, String action) {
                logger.info("key:{}, value:{}, action: {}", key, value, action);
            }
        };

        cache.addListener(listener);
        Gson gson = new Gson();
        for (int i = 1; i <= 15; i++) {
            cache.put(i, (i * i));
        }
        String json = gson.toJson(cache);
        System.out.println("current state is:  " + json);

        logger.info("getValue:{}", cache.get(3));

        cache.remove(11);
        cache.remove(13);
        json = gson.toJson(cache);
        System.out.println("current state is:  " + json);

        cache.removeListener(listener);
        Thread.sleep(1000);
        System.out.print("clearing cache.......");
        json = gson.toJson(cache);
        System.out.println("current state is:  " + json);
    }
}
