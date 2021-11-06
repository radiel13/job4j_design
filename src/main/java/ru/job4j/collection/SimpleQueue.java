package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int count;

    public T poll() {
        if (count == 0) {
            while (true) {
                try {
                    out.push(in.pop());
                    count++;
                } catch (NoSuchElementException e) {
                    break;
                }
            }
        }
        count--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}
/*
package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int count;
    private int size;

    public T poll() {

        if (size == 0) {
            throw new NoSuchElementException();
        }

        if (count == 0) {
            while (size != 0) {
                out.push(in.pop());
                count++;
                size--;
            }
        }
        count--;
        return out.pop();

    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
*/
