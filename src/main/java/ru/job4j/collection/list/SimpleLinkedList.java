package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {


    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    private Node<E>[] container;

    private int modCount;


    public SimpleLinkedList() {
        this.container =  new Node[4];
    }

    @Override
    public void add(E value) {
        if (container.length == size) {
            this.container = Arrays.copyOf(container, container.length * 2);
        }
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        container[size] = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return container[index].item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return container[point++].item;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}