package ru.otus.homework.beans.localeService;

import ru.otus.homework.exceptions.WrongInputsError;

public interface LocaleService {
    void setLocalization() throws WrongInputsError;
    void setLocalization(String lang) throws WrongInputsError;
}
