package ru.otus.homework.beans.serviceCall;

import ru.otus.homework.exceptions.CsvReaderException;
import ru.otus.homework.exceptions.WrongInputsError;
import ru.otus.homework.model.Result;
import ru.otus.homework.model.Student;

public interface ServiceCall {
    void setLanguage() throws WrongInputsError;
    void setLanguage(String abbreviationLanguage) throws WrongInputsError;
    void requestStudentName();
    void startTest() throws CsvReaderException;
    Student getStudent();
    Result getResult();
}
