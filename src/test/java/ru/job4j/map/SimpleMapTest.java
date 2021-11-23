package ru.job4j.map;

import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.List;
import ru.job4j.list.SimpleArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SimpleMapTest {
    SimpleMap<Integer, String> map;

    @Before
    public void initData() {
        map = new SimpleMap<Integer, String>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
    }

    @Test
    public void whenAddAndGetByCorrectKey() {
        Assert.assertEquals("One", map.get(1));
    }

    @Test
    public void whenAddAndSameKey() {
        Assert.assertFalse(map.put(1, "asd"));
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        assertNull(iterator.next());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());

    }

    @Test
    public void whenDeleteCheck() {
        map.remove(1);
        assertNull(map.get(1));
    }

    @Test
    public void whenDeleteCheck2() {
        assertFalse(map.remove(4));
    }

    @Test
    public void whenExpand() {
        SimpleMap<Integer, String> map1 = new SimpleMap<>();
        map1.put(0, "Zero");
        map1.put(1, "One");
        map1.put(2, "Two");
        map1.put(3, "Three");
        map1.put(4, "Four");
        map1.put(5, "Five");
        map1.put(6, "Six");
        map1.put(7, "Seven");
        map1.put(8, "Eight");
        map1.put(9, "Nine");
        map1.put(10, "Ten");
        map1.put(11, "Eleven");
        map1.put(12, "Twelve");
        Assert.assertEquals("One", map1.get(1));
        Assert.assertEquals("Six", map1.get(6));
        Assert.assertEquals("Seven", map1.get(7));
        Assert.assertEquals("Ten", map1.get(10));

        Assert.assertTrue(map1.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        map = new SimpleMap<Integer, String>();
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = map.iterator();
        map.put(4, "Four");
        iterator.next();
    }
}