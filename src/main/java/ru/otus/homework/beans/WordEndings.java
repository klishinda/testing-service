package ru.otus.homework.beans;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

@Setter
public class WordEndings {
    @Autowired
    private MessageManager messageManager;

    String getWordEnding (int correctAnswers) {
        Locale local = messageManager.getLocale();

        if (local != Locale.forLanguageTag("en-US")) {
            if (correctAnswers >= 11 && correctAnswers <= 19) {
                return messageManager.getMessage("result.ending_ov");
            } else {
                int mod = correctAnswers % 10;

                if (mod == 2 || mod == 3 || mod == 4) {
                    return messageManager.getMessage("result.ending_a");
                } else if (mod == 1) {
                    return "";
                } else {
                    return messageManager.getMessage("result.ending_ov");
                }
            }
        }
        else {
            if (correctAnswers != 1) {
                return messageManager.getMessage("result.ending_many");
            }
            else {
                return "";
            }
        }
    }
}
