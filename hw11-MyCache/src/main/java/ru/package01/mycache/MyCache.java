package ru.package01.mycache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V>, HwListener<K, V> {
    //Надо реализовать эти методы
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);
    Map<K, V> myCache = new WeakHashMap<K, V>();
    private final List<HwListener<K, V>> listenersList = new ArrayList<>();

    MyCache(Map myCache) {
        this.myCache = myCache;
    }

    public MyCache() {

    }

    @Override
    public void put(K key, V value) {
        myCache.put(key, value);
        notify(key, value, "put");
    }

    @Override
    public void remove(K key) {
        notify(key, myCache.get(key), "remove");
        myCache.remove(key);
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
