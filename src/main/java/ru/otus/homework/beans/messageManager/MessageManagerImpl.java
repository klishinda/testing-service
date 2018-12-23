package ru.otus.homework.beans.messageManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageManagerImpl implements MessageManager{
    private Locale language;

    private final MessageSource messageSource;

    @Autowired
    public MessageManagerImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    public String getMessage(String parameterName) {
        return messageSource.getMessage(parameterName, null, language);
    }

    public String getMessage(String parameterName, Object[] listOfParameters) {
        return messageSource.getMessage(parameterName, listOfParameters, language);
    }

    public void setLocale(Locale language) {
        this.language = language;
    }

    public Locale getLocale() {
        return language;
    }
}
