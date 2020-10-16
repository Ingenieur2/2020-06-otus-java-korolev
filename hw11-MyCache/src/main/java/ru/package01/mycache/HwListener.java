package ru.package01.mycache;


public interface HwListener<K, V> {
    void notify(K key, V value, String action);
}
