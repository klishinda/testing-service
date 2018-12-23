package ru.otus.homework.beans.finalResults;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework.beans.messageManager.MessageManagerImpl;
import ru.otus.homework.beans.wordEndings.WordEndingsImpl;
import ru.otus.homework.model.Result;
import ru.otus.homework.model.Student;

@Service
@Setter
public class FinalResultsPrinterPrinterImpl implements FinalResultsPrinter {
    private final MessageManagerImpl messageManager;

    private final WordEndingsImpl wordEndings;

    @Autowired
    public FinalResultsPrinterPrinterImpl(MessageManagerImpl messageManager, WordEndingsImpl wordEndings) {
        this.messageManager = messageManager;
        this.wordEndings = wordEndings;
    }

    public void printResults(Student student, Result result) {
        System.out.println("_____________________________________________________________________");
        System.out.println(messageManager.getMessage("service_runner.results"));
        System.out.println(messageManager.getMessage("service_runner.results_detail_info", new String[] {student.getName(), student.getSurname(), String.valueOf(result.getCorrectAnswers()), wordEndings.getWordEnding(result.getCorrectAnswers()), String.valueOf(result.getAllQuestions())}));

    }
}
