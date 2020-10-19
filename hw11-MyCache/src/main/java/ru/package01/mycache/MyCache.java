package ru.package01.mycache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MyCache<K, V> implements HwCache<K, V>, HwListener<K, V> {
    //Надо реализовать эти методы
    private static final int MAX_MY_CACHE_SIZE = 5;
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);
    private Map<K, V> myCache = new WeakHashMap<K, V>();
    private List<K> listOfKeys = new ArrayList<>();
    private final List<HwListener<K, V>> listenersList = new ArrayList<>();

    MyCache(Map myCache) {
        this.myCache = myCache;
    }

    public MyCache() {

    }

    @Override
    public void put(K key, V value) {
        if (myCache.size() < MAX_MY_CACHE_SIZE) {
            myCache.put(key, value);
            listOfKeys.add(key);
        } else {
            myCache.put(key, value);
            myCache.remove(listOfKeys.get(0));
            listOfKeys.remove(0);
            listOfKeys.add(key);
        }
        notify(key, value, "put");
    }

    @Override
    public void remove(K key) {
        notify(key, myCache.get(key), "remove");
        myCache.remove(key);
        listOfKeys.remove(key);
    }

    @Override
    public V get(K key) {
        notify(key, myCache.get(key), "get");
        return myCache.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listenersList.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listenersList.remove(listener);
    }

    @Override
    public void notify(K key, V value, String action) {
        logger.info("key:{}, value:{}, action: {}", key, value, action);
    }
}
