package ru.otus.homework.beans.messageManager;

import java.util.Locale;

public interface MessageManager {
    String getMessage(String parameterName);

    String getMessage(String parameterName, Object[] listOfParameters);

    void setLocale(Locale language);

    Locale getLocale();
}
