package ru.otus.homework.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    private Result res = new Result(3, 5);

    @Test
    void getCorrectAnswers() {
        assertThat(res.getCorrectAnswers()).isEqualTo(3);
    }

    @Test
    void getAllQuestions() {
        assertThat(res.getAllQuestions()).isEqualTo(5);
    }
}