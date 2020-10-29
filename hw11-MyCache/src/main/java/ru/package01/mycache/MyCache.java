package ru.package01.mycache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class MyCache<K, V> implements HwCache<K, V> {

    private static final long MAX_MY_CACHE_SIZE = 5;
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);
    private final Map<K, V> myCache = new WeakHashMap<K, V>();
    LinkedList<K> referenceList;

    public MyCache() {

    }

    @Override
    public void put(K key, V value) {
        if (referenceList != null) {
            referenceList.addLast(key);
            if (myCache.size() < MAX_MY_CACHE_SIZE) {
                myCache.put(key, value);
            } else {
                myCache.put(key, value);
                myCache.remove(referenceList.getFirst());
                referenceList.removeFirst();
            }
            notify(key, value, "put");
        }
    }

    @Override
    public void remove(K key) {
        if (myCache.containsKey(key)) {
            notify(key, myCache.get(key), "remove");
            myCache.remove(key);
            referenceList.remove(key);
        }
    }

    @Override
    public V get(K key) {
        notify(key, myCache.get(key), "get");
        return myCache.get(key);
    }

    @Override
    public void addListener() {
        if (referenceList == null) {
            referenceList = new LinkedList<>();
        }
    }

    @Override
    public void removeListener() throws InterruptedException {
        referenceList = null;
        System.gc();
        Thread.sleep(2000);
    }

    public void notify(K key, V value, String action) {
        logger.info("key:{}, value:{}, action: {}", key, value, action);
    }

}
