package ru.otus.homework.beans;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import ru.otus.homework.exceptions.WrongInputsError;

import java.util.*;

@Setter
public class LocaleService {
    private static final int MAX_ERROR_ATTEMPTS = 3;
    private int errorCounts = 0;
    private Map<String, Object> propertyMap;

    @Autowired
    private MessageManager messageManager;

    @Autowired
    Environment environment;

    public void setLocalization() throws WrongInputsError {
        checkWrongAttempts();

        Scanner inputStream = new Scanner(System.in);
        getPropertyMap();
        System.out.println("Enter your preferred language, enabled " + propertyMap.get("lang") + ":  ");
        String lang = inputStream.nextLine();

        getLocalizationParameter(lang);
    }

    private void checkWrongAttempts() throws WrongInputsError {
        if (errorCounts >= MAX_ERROR_ATTEMPTS) {
            throw new WrongInputsError("You are too stupid for this test, go away!!!");
        }
    }

    private void getLocalizationParameter(String localParameter) throws WrongInputsError {
        if (propertyMap.containsKey("lang_localization." + localParameter)) {
            messageManager.setLocale(Locale.forLanguageTag(String.valueOf(propertyMap.get("lang_localization." + localParameter))));
        }
        else {
            System.out.println("Your input is wrong, please try again.");
            errorCounts++;
            setLocalization();
        }
    }

    private void getPropertyMap() {
        for (PropertySource<?> propertySource : ((AbstractEnvironment) environment).getPropertySources()) {
            if (propertySource instanceof MapPropertySource) {
                propertyMap = new HashMap(((MapPropertySource) propertySource).getSource());
            }
        }
    }
}
