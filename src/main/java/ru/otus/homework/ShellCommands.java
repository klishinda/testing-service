package ru.otus.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;
import ru.otus.homework.beans.messageManager.MessageManagerImpl;
import ru.otus.homework.beans.serviceCall.ServiceCall;
import ru.otus.homework.beans.serviceCall.ServiceCallImpl;
import ru.otus.homework.exceptions.CsvReaderException;
import ru.otus.homework.exceptions.WrongInputsError;

import javax.validation.constraints.Size;

import static org.springframework.shell.table.CellMatchers.at;

@ShellComponent
public class ShellCommands {

    private final ServiceCall serviceCall;
    private final MessageManagerImpl messageManager;

    @Autowired
    public ShellCommands(ServiceCallImpl serviceCall, MessageManagerImpl messageManager) {
        this.serviceCall = serviceCall;
        this.messageManager = messageManager;
    }


    @ShellMethod("Choose your preferred language")
    private void setLanguage() throws WrongInputsError {
        serviceCall.setLanguage();
    }

    @ShellMethod("Choose your preferred language (if you know, what do you want)")
    private void setLanguageWithShell(@ShellOption @Size(min=2, max=2)  String abbreviationLanguage) throws WrongInputsError {
        serviceCall.setLanguage(abbreviationLanguage);
    }

    @ShellMethod("Set student's parameters")
    private void requestStudentName(){
        serviceCall.requestStudentName();
    }

    @ShellMethod("Start testing")
    @ShellMethodAvailability("availabilityStartTest")
    private void startTest() throws CsvReaderException {
        serviceCall.startTest();
    }

    @ShellMethod("Show results")
    @ShellMethodAvailability("availabilityResults")
    private Table printResults() {
        String[][] table = new String[3][2];
        TableModel model = new ArrayTableModel(table);
        TableBuilder tableBuilder = new TableBuilder(model);

        table[0][0] = messageManager.getMessage("shell.student");
        table[1][0] = messageManager.getMessage("shell.right_answers");
        table[2][0] = messageManager.getMessage("shell.all_questions");
        table[0][1] = serviceCall.getStudent().getName() + " " + serviceCall.getStudent().getSurname();
        table[1][1] = String.valueOf(serviceCall.getResult().getCorrectAnswers());
        table[2][1] = String.valueOf(serviceCall.getResult().getAllQuestions());
        tableBuilder.on(at(1, 1)).addAligner(SimpleHorizontalAligner.values()[1]);
        tableBuilder.on(at(1, 1)).addAligner(SimpleVerticalAligner.values()[1]);
        tableBuilder.on(at(2, 1)).addAligner(SimpleHorizontalAligner.values()[1]);
        tableBuilder.on(at(2, 1)).addAligner(SimpleVerticalAligner.values()[1]);

        return tableBuilder.addFullBorder(BorderStyle.fancy_light).build();
    }

    @ShellMethod("Full testing cycle")
    private Table start() throws CsvReaderException, WrongInputsError {
        serviceCall.setLanguage();
        serviceCall.requestStudentName();
        serviceCall.startTest();
        return printResults();
    }

    private Availability availabilityStartTest() {
        return serviceCall.getStudent() != null
                ? Availability.available()
                : Availability.unavailable("Please fill the student's parameters (command request-student-name)");
    }

    private Availability availabilityResults() {
        return serviceCall.getResult() != null && serviceCall.getStudent() != null
                ? Availability.available()
                : Availability.unavailable("Please fill the student's parameters (command request-student-name) and pass the test (command start-test)");
    }
}