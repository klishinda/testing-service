package ru.otus.homework.beans.csvReader;

import ru.otus.homework.exceptions.CsvReaderException;
import ru.otus.homework.model.Questionnaire;

import java.util.ArrayList;

public interface CsvReader {
    ArrayList<Questionnaire> readCsv() throws CsvReaderException;
}
