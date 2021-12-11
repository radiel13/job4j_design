package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info result = new Info(0, 0, 0);

        if (previous.equals(current)) return result;

        Map<Integer, String> treeMap1 = previous.stream().collect(Collectors.toMap(User::getId, User::getName));
        Map<Integer, String> treeMap2 = current.stream().collect(Collectors.toMap(User::getId, User::getName));

        Map<Integer, String> treeMapAll = new HashMap<Integer, String>();
        treeMapAll.putAll(treeMap1);
        treeMapAll.putAll(treeMap2);

        for (Map.Entry<Integer, String> user : treeMapAll.entrySet()) {
            int key = user.getKey();
            if (!treeMap1.containsKey(key) && treeMap2.containsKey(key)) {
                result.setAdded(result.getAdded() + 1);
            }

            if (!treeMap2.containsKey(key) && treeMap1.containsKey(key)) {
                result.setDeleted(result.getDeleted() + 1);
            }

            if ((treeMap1.containsKey(key) && treeMap2.containsKey(key)) && (!treeMapAll.get(key).equals(treeMap1.get(key)))) {
                result.setChanged(result.getChanged() + 1);
            }
        }

        return result;
    }

}