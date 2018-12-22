package ru.otus.homework.model;

public class Student {
    private String surname;

    private String name;

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Student(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }
}
