package ru.otus.homework.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageManager {
    private Locale language;

    @Autowired
    private MessageSource messageSource;


    String getMessage(String parameterName) {
        return messageSource.getMessage(parameterName, null, language);
    }

    String getMessage(String parameterName, Object[] listOfParameters) {
        return messageSource.getMessage(parameterName, listOfParameters, language);
    }

    void setLocale(Locale language) {
        this.language = language;
    }

    Locale getLocale() {
        return language;
    }
}
