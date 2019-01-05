package ru.otus.homework.beans.localeService;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework.beans.messageManager.MessageManagerImpl;
import ru.otus.homework.exceptions.WrongInputsError;
import ru.otus.homework.beans.AppProps;

import java.util.Locale;
import java.util.Scanner;

@Service
@Setter
public class LocaleServiceImpl implements LocaleService {
    private static final int MAX_ERROR_ATTEMPTS = 3;
    private int errorCounts = 0;

    private final AppProps appProps;

    private final MessageManagerImpl messageManager;

    @Autowired
    public LocaleServiceImpl(MessageManagerImpl messageManager, AppProps appProps) {
        this.messageManager = messageManager;
        this.appProps = appProps;
    }

    public void setLocalization() throws WrongInputsError {
        checkWrongAttempts();

        Scanner inputStream = new Scanner(System.in);
        System.out.println("Enter your preferred language, enabled " + appProps.getAllLangs() + ":  ");
        String lang = inputStream.nextLine();

        getLocalizationParameter(lang);
    }

    public void setLocalization(String lang) throws WrongInputsError {
        checkWrongAttempts();

        getLocalizationParameter(lang);
    }

    private void checkWrongAttempts() throws WrongInputsError {
        if (errorCounts >= MAX_ERROR_ATTEMPTS) {
            throw new WrongInputsError("You are too stupid for this test, go away!!!");
        }
    }

    private void getLocalizationParameter(String localParameter) throws WrongInputsError {
        if (appProps.getLangLocalization().containsKey(localParameter)) {
            messageManager.setLocale(Locale.forLanguageTag(String.valueOf(appProps.getLangLocalization().get(localParameter))));
        }
        else {
            System.out.println("Your input is wrong, please try again.");
            errorCounts++;
            setLocalization();
        }
    }
}
