package com.map;


import java.util.LinkedList;
import java.util.List;


public class HashMapCustom<K, V> {
    private List<Entry<K, V>>[] buckets;
    private int size;
    private double loadFactor;

    private static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;

        }
    }

    public HashMapCustom() {
        int defaultCapacity = 16;
        buckets = new LinkedList[defaultCapacity];
        loadFactor = 0.75;
        size = 0;
    }

    public HashMapCustom(int capacity) {
        buckets = new LinkedList[capacity];
        loadFactor = 0.75;
        size = 0;
    }

    public void put(K key, V value) {
        resizeAndRehash();
        int index = getIndex(key, buckets.length);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.hashCode() == key.hashCode() && entry.key.equals(key)) {
                entry.value = value;
                size++;
                return;
            }
        }
        buckets[index].add(new Entry<>(key, value));
        size++;

    }

    public V get(K key) {
        int index = getIndex(key, buckets.length);
        if (buckets[index] == null) {
            return null;
        }
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.hashCode() == key.hashCode() && entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key, buckets.length);
        if (buckets[index] == null) {
            return null;
        }
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.hashCode() == key.hashCode() && entry.key.equals(key)) {
                buckets[index].remove(entry);
                size--;
                return entry.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int getIndex(K key, int length) {

        return Math.abs(key.hashCode() % length);
    }

    private void resizeAndRehash() {
        List<Entry<K, V>>[] newBuckets;
        if ((double) size / buckets.length > loadFactor) {
            newBuckets = new LinkedList[buckets.length * 2];
            for (List<Entry<K, V>> bucket : buckets) {
                if (bucket == null)
                    continue;
                for (Entry<K, V> entry : bucket) {
                    int index = getIndex(entry.key, newBuckets.length);
                    if (newBuckets[index] == null) {
                        newBuckets[index] = new LinkedList<>();

                    }
                    newBuckets[index].add(entry);

                }
            }
            buckets = newBuckets;
        }

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean next = false;
        for (List<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (next) {
                        sb.append(", ");
                    }
                    sb.append(entry.key + " " + entry.value);
                    next = true;
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}

