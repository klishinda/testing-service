package ru.otus.homework.beans;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.homework.model.Result;
import ru.otus.homework.model.Student;

@Setter
public class FinalResults {
    @Autowired
    private MessageManager messageManager;

    @Autowired
    private WordEndings wordEndings;

    public void printResults(Student student, Result result) {
        System.out.println("_____________________________________________________________________");
        System.out.println(messageManager.getMessage("service_runner.results"));
        System.out.println(messageManager.getMessage("service_runner.results_detail_info", new String[] {student.getName(), student.getSurname(), String.valueOf(result.getCorrectAnswers()), wordEndings.getWordEnding(result.getCorrectAnswers()), String.valueOf(result.getAllQuestions())}));

    }
}
