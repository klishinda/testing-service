package ru.otus.homework.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QuestionnaireTest {

    private ArrayList<Questionnaire> quiz = new ArrayList();
    private Questionnaire oneQuestion = new Questionnaire("Один вопрос", "Один ответ");

    @Test
    void getQuestion() {
        quiz.add(new Questionnaire("Тест?", "Да"));
        quiz.add(new Questionnaire("Сложный?", "Нет"));
        assertThat(quiz.get(0).getQuestion()).isEqualTo("Тест?");
        assertThat(quiz.get(1).getQuestion()).isEqualTo("Сложный?");
        assertThat(quiz.get(1).getQuestion()).isNotEqualTo(oneQuestion.getQuestion());
    }

    @Test
    void getAnswer() {
        quiz.add(new Questionnaire("Много вопросов?", "Нет"));
        quiz.add(new Questionnaire("Сколько ответов на один вопрос?", "Один ответ"));
        assertThat(quiz.size()).isEqualTo(2);
        assertThat(quiz.get(0).getAnswer()).isEqualTo("Нет");
        assertThat(quiz.get(1).getAnswer()).isEqualTo(oneQuestion.getAnswer());

    }
}