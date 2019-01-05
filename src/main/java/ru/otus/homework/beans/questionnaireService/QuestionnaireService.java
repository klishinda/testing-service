package ru.otus.homework.beans.questionnaireService;

import ru.otus.homework.model.Questionnaire;
import ru.otus.homework.model.Result;
import ru.otus.homework.model.Student;

import java.util.ArrayList;

public interface QuestionnaireService {
    Student requestStudentName();

    Result startQuestionnaire(ArrayList<Questionnaire> quizArray);
}
