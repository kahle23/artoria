package com.apyhs.artoria.collection;

import java.util.Map;

/**
 * Strengthen hashMap.
 * @author Kahle
 */
public class HashMap<K, V> extends java.util.HashMap<K, V> {

    public HashMap() {
        super();
    }

    public HashMap(K key, V value) {
        super();
        this.set(key, value);
    }

    public HashMap(Map<? extends K, ? extends V> m) {
        super();
        this.set(m);
    }

    public HashMap<K, V> set(K key, V value) {
        super.put(key, value);
        return this;
    }

    public HashMap<K, V> set(Map<? extends K, ? extends V> m) {
        super.putAll(m);
        return this;
    }

}
