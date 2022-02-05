package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    // solution 1
    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {

        while (row < data.length - 1 && data[row].length == 0) {
            row++;
        }
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        if (column == data[row].length - 1) {
            int temp = column;
            //CHECKSTYLE:OFF
            return data[row++][(column -= temp) + temp];
        }

        return data[row][column++];
    }
}
