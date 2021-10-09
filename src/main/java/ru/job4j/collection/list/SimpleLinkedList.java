package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;
    private int modCount;

    public SimpleLinkedList() {
    }

    @Override
    public void add(E value) {

        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> f = first;
        int n = 0;
        while (n < index) {
            f = f.next;
            n++;
        }
        return f.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> curNode = first;


            @Override
            public boolean hasNext() {
                return curNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> temp = curNode;
                curNode = curNode.next;
                return temp.item;
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