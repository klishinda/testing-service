package ru.otus.homework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.beans.*;
import ru.otus.homework.exceptions.CsvReaderException;
import ru.otus.homework.exceptions.WrongInputsError;
import ru.otus.homework.model.Questionnaire;
import ru.otus.homework.model.Result;
import ru.otus.homework.model.Student;

import java.util.ArrayList;

@Slf4j
@RestController
public class TestingServiceController {

    @Autowired
    private LocaleService localeService;

    @Autowired
    private ScanStudent scanStudent;

    @Autowired
    private CsvReader csvReader;

    @Autowired
    private FinalResults finalResults;

    @Bean
    public void startService() throws WrongInputsError, CsvReaderException {
        localeService.setLocalization();
        Student student = scanStudent.getName();
        ArrayList<Questionnaire> quizArray = csvReader.readCsv();
        Result result = scanStudent.startQuestionnaire(quizArray);
        finalResults.printResults(student, result);
    }
}
