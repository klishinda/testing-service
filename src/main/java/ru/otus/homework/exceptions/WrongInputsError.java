package ru.otus.homework.exceptions;

public class WrongInputsError extends Exception {
    public WrongInputsError(String errorMsg) {
        super(errorMsg);
    }
}
