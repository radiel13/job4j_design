package ru.job4j.map;

import java.util.*;
import java.time.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Paul", 0, new GregorianCalendar(2017, Calendar.FEBRUARY, 13));
        User user2 = new User("Paul", 0, new GregorianCalendar(2017, Calendar.FEBRUARY, 13));

        /*Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }*/

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());

        System.out.println(user1.equals(user2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
