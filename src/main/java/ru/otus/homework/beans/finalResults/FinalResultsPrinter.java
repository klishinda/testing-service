package ru.otus.homework.beans.finalResults;

import ru.otus.homework.model.Result;
import ru.otus.homework.model.Student;

public interface FinalResultsPrinter {
    void printResults(Student student, Result result);
}
