package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {

        for (T val : set) {
            if (val == null && value == null) {
                return false;
            }

            if(val == null){
                continue;
            }

            if (val.equals(value)) {
                return false;
            }
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T val : set) {
            if (val == null && value == null) {
                return true;
            }
        }
        for (T val : set) {
            if (val.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

}