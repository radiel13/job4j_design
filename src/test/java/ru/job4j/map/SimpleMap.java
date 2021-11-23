package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
  /*      if (key == null) {
            return false;
        }

        int i = indexFor(hash(key.hashCode()));
        expand();
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                if (entry.key.hashCode() == key.hashCode() && ((key == entry.key) || (entry.key.equals(key)))) {
                    return false;
                }
            }
        }

        table[i] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;*/
        int hs = key.hashCode();
        int hash = hash(hs);
        int index = indexFor(hash);
        expand();

        if (table[index] == null) {
            MapEntry<K, V> mapEntry = new MapEntry<>(key, value);
            table[index] = mapEntry;
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    private int hash(int hashCode) {
        int h;
        return ((hashCode == 0) ? 0 : (h = hashCode) ^ (h >>> 16));
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        if ((float) (count / capacity) >= LOAD_FACTOR) {
            MapEntry<K, V>[] tempTable = table;
            table = new MapEntry[capacity * 2];
            for (MapEntry<K, V> entry : tempTable) {
                if (entry != null) {
                    table[indexFor(hash(entry.key.hashCode()))] = entry;
                }
            }
        }
    }

    @Override
    public V get(K key) {
/*        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                if (entry.key.hashCode() == key.hashCode() && (key == entry.key) || (entry.key.equals(key))) {
                    return entry.value;
                }
            }
        }*/
        int hs = key.hashCode();
        int hash = hash(hs);
        int index = indexFor(hash);

        if (table[index] == null) {
            return null;
        }

        if (table[index].key.hashCode() == hs && table[index].key.equals(key)) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
/*        if (key == null) {
            return false;
        }

        MapEntry<K, V> tempEntry = table[indexFor(hash(key.hashCode()))];
        if (tempEntry != null && key.hashCode() == tempEntry.key.hashCode() && key.equals(tempEntry.key)) {
            table[indexFor(hash(key.hashCode()))] = null;
            count--;
            modCount++;
            return true;
        }*/
        int hs = key.hashCode();
        int hash = hash(hs);
        int index = indexFor(hash);

        if (table[index] == null) {
            return false;
        }

        if (table[index].key.hashCode() == key.hashCode() && table[index].key.equals(key)) {
            table[index] = null;
            return true;
        }
        count--;
        modCount++;
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                for (int i = point; i < table.length-1; i++){
                    if(table[i] != null){
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }


                if (table[point] != null) {
                    K key = table[point].key;
                    point++;
                    return key;
                } else {
                    point++;
                    return null;
                }
            }

        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}