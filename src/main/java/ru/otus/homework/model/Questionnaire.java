package ru.otus.homework.model;

public class Questionnaire {
    private String question;

    private String answer;

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Questionnaire(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
