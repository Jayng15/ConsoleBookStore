package service.adt;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import service.adt.*;
import service.adt.interfaces.*;

public class HashMapADT<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private Entry<K, V>[] table;
    private int size;

    public HashMapADT() {
        this.table = new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int hash = hash(key);
        int index = hash % table.length;
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<K, V> entry : table) {
            for (Entry<K, V> e = entry; e != null; e = e.next) {
                if (Objects.equals(e.value, value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int hash = hash(key);
        int index = hash % table.length;
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (size >= table.length * LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        int hash = hash(key);
        int index = hash % table.length;
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        addEntry(hash, key, value, index);
        size++;
        return null;
    }

    private void addEntry(int hash, K key, V value, int index) {
        Entry<K, V> entry = table[index];
        table[index] = new Entry<>(hash, key, value, entry);
    }

    private void resize() {
        int newCapacity = table.length * 2;
        Entry<K, V>[] newTable = new Entry[newCapacity];
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                int index = entry.hash % newCapacity;
                Entry<K, V> next = entry.next;
                entry.next = newTable[index];
                newTable[index] = entry;
                entry = next;
            }
        }
        table = newTable;
    }

    @Override
    public V remove(Object key) {
        int hash = hash(key);
        int index = hash % table.length;
        Entry<K, V> prev = null;
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
            prev = entry;
        }
        return null;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    private int hash(Object key) {
        return Objects.hashCode(key);
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Entry<K, V> entry : table) {
            for (Entry<K, V> e = entry; e != null; e = e.next) {
                keySet.add(e.key);
            }
        }
        return keySet;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayListADT<>();
        for (Entry<K, V> entry : table) {
            for (Entry<K, V> e = entry; e != null; e = e.next) {
                values.add(e.value);
            }
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<>();
        for (Entry<K, V> entry : table) {
            for (Entry<K, V> e = entry; e != null; e = e.next) {
                entrySet.add(e);
            }
        }
        return entrySet;
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Entry<K, V> next;

        Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

}
