package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Person person = new Person(false, 30, new Contact("11-111"), new String[]{"Worker", "Married"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);
        //final Character character = new Character(true, 78, new Contact("rad"), new String[] {"Human", "Sorc"});

        /* Преобразуем объект person в json-строку. */
        //final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //System.out.println(gson.toJson(person));
        //System.out.println(gson.toJson(character));

        /* Модифицируем json-строку */
        //final String personJson = gson.toJson(person);
        //final String characterJson = gson.toJson(character);
               /* "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";*/
        //final Person personMod = gson.fromJson(personJson, Person.class);
        //final Character characterMod = gson.fromJson(characterJson, Character.class);
        //System.out.println(personMod);
        //System.out.println(characterMod);
        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person).toString());
    }
}