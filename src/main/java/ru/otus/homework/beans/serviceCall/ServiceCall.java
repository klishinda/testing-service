package ru.otus.homework.beans.serviceCall;

import org.springframework.shell.table.Table;
import ru.otus.homework.exceptions.CsvReaderException;
import ru.otus.homework.exceptions.WrongInputsError;

public interface ServiceCall {
    void setLanguage() throws WrongInputsError;
    void setLanguage(String abbreviationLanguage) throws WrongInputsError;
    void requestStudentName();
    void startTest() throws CsvReaderException;
    Table printResults();
    Table start() throws CsvReaderException, WrongInputsError;
}
