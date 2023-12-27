package com.example.gson;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        // Создаем объект Gson
        Gson gson = new Gson();

        // Создаем объект для сериализации
        Person person = new Person("John", "Doe", 30);

        // Сериализуем объект в JSON
        String json = gson.toJson(person);
        System.out.println(json);

        // Десериализуем объект из JSON
        Person personFromJson = gson.fromJson(json, Person.class);
        System.out.println(personFromJson);
    }
}
