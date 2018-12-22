package ru.otus.homework.model;

public class Result {
    private int correctAnswers;
    private int allQuestions;

    public Result (int correctAnswers, int allQuestions) {
        this.correctAnswers = correctAnswers;
        this.allQuestions = allQuestions;
    }

    public int getCorrectAnswers() { return correctAnswers;}

    public int getAllQuestions() {return allQuestions;}
}
