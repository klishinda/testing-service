package ru.otus.homework.beans;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.homework.model.Questionnaire;
import ru.otus.homework.model.Result;
import ru.otus.homework.model.Student;

import java.util.ArrayList;
import java.util.Scanner;

@Setter
public class ScanStudent {
    private int correctAnswersCounter = 0;
    private Scanner inputStream;

    @Autowired
    private MessageManager messageManager;

    public Student getName() {
        inputStream = new Scanner(System.in);
        System.out.println(messageManager.getMessage("scan_student.name"));
        String name = inputStream.nextLine();
        System.out.println(messageManager.getMessage("scan_student.surname"));
        String surname = inputStream.nextLine();
        return new Student(name, surname);
    }

    public Result startQuestionnaire(ArrayList<Questionnaire> quizArray) {
        if (quizArray.size() != 0) {
            System.out.println(messageManager.getMessage("scan_student.questions_ready"));
        }
        else {
            System.out.println(messageManager.getMessage("scan_student.questions_not_ready"));
            return new Result(0, 0);
        }
        for (Questionnaire q : quizArray) {
            System.out.println(messageManager.getMessage("scan_student.question") + " " + q.getQuestion());
            String studentsAnswer = inputStream.nextLine();
            if (studentsAnswer.equals(q.getAnswer())) {
                correctAnswersCounter++;
            }
        }

        return new Result(correctAnswersCounter, quizArray.size());
    }
}
