package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public boolean checkEven(int[] data) {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return index < data.length && checkEven(data);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        while (data[index] % 2 != 0) {
            index++;
        }

        return data[index++];
    }

}