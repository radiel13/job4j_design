package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> el = findBy(parent);
        if (el.isEmpty()) {
            return false;
        }
        Node<E> element = el.get();
        Optional<Node<E>> childEl = findBy(child);
        if (childEl.isPresent()) {
            return false;
        }

        element.children.add(new Node<>(child));
        return true;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                return Optional.of(el);
            }
            data.addAll(el.children);
        }
        return Optional.empty();
    }

    @Override
    public boolean isBinary() {
        Optional<Node<E>> node = findByPredicate(v -> v.children.size() > 2);
        return node.isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(v -> v.value.equals(value));
    }
}