package ru.otus.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import ru.otus.homework.beans.csvReader.CsvReader;
import ru.otus.homework.beans.finalResults.FinalResultsPrinter;
import ru.otus.homework.beans.localeService.LocaleServiceImpl;
import ru.otus.homework.beans.scanStudent.QuestionnaireServiceImpl;
import ru.otus.homework.exceptions.CsvReaderException;
import ru.otus.homework.exceptions.WrongInputsError;
import ru.otus.homework.model.Questionnaire;
import ru.otus.homework.model.Result;
import ru.otus.homework.model.Student;

import java.util.ArrayList;


@Controller
public class TestingServiceController {

    private final LocaleServiceImpl localeService;
    private final QuestionnaireServiceImpl questionnaireService;
    private final CsvReader csvReader;
    private final FinalResultsPrinter finalResultsPrinter;

    @Autowired
    public TestingServiceController(LocaleServiceImpl localeService, QuestionnaireServiceImpl questionnaireService, CsvReader csvReader, FinalResultsPrinter finalResultsPrinter) {
        this.localeService = localeService;
        this.questionnaireService = questionnaireService;
        this.csvReader = csvReader;
        this.finalResultsPrinter = finalResultsPrinter;
    }

    @Bean
    public void startService() throws WrongInputsError, CsvReaderException {
        localeService.setLocalization();
        Student student = questionnaireService.requestStudentName();
        ArrayList<Questionnaire> quizArray = csvReader.readCsv();
        Result result = questionnaireService.startQuestionnaire(quizArray);
        finalResultsPrinter.printResults(student, result);
    }
}
