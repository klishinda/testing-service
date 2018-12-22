package ru.otus.homework.exceptions;

public class CsvReaderException extends Exception {
    public CsvReaderException(String errorMsg) {
        super(errorMsg);
    }
}
