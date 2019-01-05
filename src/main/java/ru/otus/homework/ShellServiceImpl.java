package ru.otus.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;
import ru.otus.homework.beans.csvReader.CsvReader;
import ru.otus.homework.beans.localeService.LocaleServiceImpl;
import ru.otus.homework.beans.messageManager.MessageManagerImpl;
import ru.otus.homework.beans.questionnaireService.QuestionnaireServiceImpl;
import ru.otus.homework.exceptions.CsvReaderException;
import ru.otus.homework.exceptions.WrongInputsError;
import ru.otus.homework.model.Questionnaire;
import ru.otus.homework.model.Result;
import ru.otus.homework.model.Student;

import javax.validation.constraints.Size;
import java.util.ArrayList;

import static org.springframework.shell.table.CellMatchers.at;

@ShellComponent
public class ShellServiceImpl {
    private final LocaleServiceImpl localeService;
    private final QuestionnaireServiceImpl questionnaireService;
    private final CsvReader csvReader;
    private final MessageManagerImpl messageManager;

    private Student student;
    private Result result;

    @Autowired
    public ShellServiceImpl(LocaleServiceImpl localeService, QuestionnaireServiceImpl questionnaireService, CsvReader csvReader, MessageManagerImpl messageManager) {
        this.localeService = localeService;
        this.questionnaireService = questionnaireService;
        this.csvReader = csvReader;
        this.messageManager = messageManager;
    }

    @ShellMethod("Choose your preferred language")
    public void setLanguage() throws WrongInputsError {
        localeService.setLocalization();
    }

    @ShellMethod("Choose your preferred language (if you know, what do you want)")
    public void setLanguageWithShell(@ShellOption @Size(min=2, max=2)  String abbreviationLanguage) throws WrongInputsError {
        localeService.setLocalization(abbreviationLanguage);
    }

    @ShellMethod("Set student's parameters")
    public void requestStudentName(){
        student = questionnaireService.requestStudentName();
    }

    @ShellMethod("Start testing")
    public void startTest() throws CsvReaderException {
        ArrayList<Questionnaire> quizArray = csvReader.readCsv();
        result = questionnaireService.startQuestionnaire(quizArray);
    }

    @ShellMethod("Show results")
    private Table printResults() {
        if (student == null) {
            System.out.println("Please fill the student's parameters (command request-student-name)");
            return null;
        }
        else if (result == null) {
            System.out.println("Please pass the test (command start-test)");
            return null;
        }
        else {
            String[][] table = new String[3][2];
            TableModel model = new ArrayTableModel(table);
            TableBuilder tableBuilder = new TableBuilder(model);

            table[0][0] = messageManager.getMessage("shell.student");
            table[1][0] = messageManager.getMessage("shell.right_answers");
            table[2][0] = messageManager.getMessage("shell.all_questions");
            table[0][1] = student.getName() + " " + student.getSurname();
            table[1][1] = String.valueOf(result.getCorrectAnswers());
            table[2][1] = String.valueOf(result.getAllQuestions());
            tableBuilder.on(at(1, 1)).addAligner(SimpleHorizontalAligner.values()[1]);
            tableBuilder.on(at(1, 1)).addAligner(SimpleVerticalAligner.values()[1]);
            tableBuilder.on(at(2, 1)).addAligner(SimpleHorizontalAligner.values()[1]);
            tableBuilder.on(at(2, 1)).addAligner(SimpleVerticalAligner.values()[1]);

            return tableBuilder.addFullBorder(BorderStyle.fancy_light).build();
        }
    }

    @ShellMethod("Full testing cycle")
    public Table start() throws CsvReaderException, WrongInputsError {
        setLanguage();
        requestStudentName();
        startTest();
        return printResults();
    }
}