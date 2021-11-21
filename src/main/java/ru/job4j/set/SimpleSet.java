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
/*
123
(0)1. 1 (66)
(1)2. 10 (33)
(2)3. 101 (16)
(3)4. 1011 (8)
(4)5. 10110 (4)
(5)6. 101100 (2)
(6)7. 1011000 (1)
(7)8. 10110001 Х

public static String binary(int num) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 31; i++) {
        sb.append(num % 2 == 0 ? 0 : 1);
        sb.append((i + 1) % 8 == 0 ? " " : "");
        num /= 2;
    }
    return sb.reverse().toString();
}
*
*/
