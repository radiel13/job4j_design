package ru.job4j.iterator;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;

import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        ListUtils.removeIf(input, x -> x + 5 == 7);
        assertThat(input, is(Arrays.asList(1, 3)));
    }

    @Test

    public void whenReplace() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        ListUtils.replaceIf(input, x -> x + 5 == 7, 0);
        assertThat(input, is(Arrays.asList(1, 0, 3)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 0));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(0)));
    }
}