package ru.otus.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.Table;
import ru.otus.homework.beans.serviceCall.ServiceCallImpl;
import ru.otus.homework.exceptions.CsvReaderException;
import ru.otus.homework.exceptions.WrongInputsError;

import javax.validation.constraints.Size;

@ShellComponent
public class ShellCommands {

    private final ServiceCallImpl serviceCallImpl;

    @Autowired
    public ShellCommands(ServiceCallImpl serviceCallImpl) {
        this.serviceCallImpl = serviceCallImpl;
    }


    @ShellMethod("Choose your preferred language")
    private void setLanguage() throws WrongInputsError {
        serviceCallImpl.setLanguage();
    }

    @ShellMethod("Choose your preferred language (if you know, what do you want)")
    private void setLanguageWithShell(@ShellOption @Size(min=2, max=2)  String abbreviationLanguage) throws WrongInputsError {
        serviceCallImpl.setLanguage(abbreviationLanguage);
    }

    @ShellMethod("Set student's parameters")
    private void requestStudentName(){
        serviceCallImpl.requestStudentName();
    }

    @ShellMethod(value = "Start testing")
    @ShellMethodAvailability("availabilityStartTest")
    private void startTest() throws CsvReaderException {
        System.out.println(serviceCallImpl.getStudent());
        serviceCallImpl.startTest();
    }

    @ShellMethod(value = "Show results")
    @ShellMethodAvailability("availabilityResults")
    private Table printResults() {
        return serviceCallImpl.printResults();
    }

    @ShellMethod("Full testing cycle")
    private Table start() throws CsvReaderException, WrongInputsError {
        return serviceCallImpl.start();
    }

    private Availability availabilityStartTest() {
        return serviceCallImpl.getStudent() != null
                ? Availability.available()
                : Availability.unavailable("Please fill the student's parameters (command request-student-name)");
    }

    private Availability availabilityResults() {
        System.out.println(serviceCallImpl.getStudent());
        return serviceCallImpl.getResult() != null && serviceCallImpl.getStudent() != null
                ? Availability.available()
                : Availability.unavailable("Please fill the student's parameters (command request-student-name) and pass the test (command start-test)");
    }
}