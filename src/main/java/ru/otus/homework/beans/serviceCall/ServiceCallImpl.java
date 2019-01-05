package ru.otus.homework.beans.serviceCall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework.beans.csvReader.CsvReader;
import ru.otus.homework.beans.localeService.LocaleServiceImpl;
import ru.otus.homework.beans.messageManager.MessageManagerImpl;
import ru.otus.homework.beans.questionnaireService.QuestionnaireServiceImpl;
import ru.otus.homework.exceptions.CsvReaderException;
import ru.otus.homework.exceptions.WrongInputsError;
import ru.otus.homework.model.Questionnaire;
import ru.otus.homework.model.Result;
import ru.otus.homework.model.Student;

import java.util.ArrayList;

@Service
public class ServiceCallImpl implements ServiceCall {
    private final LocaleServiceImpl localeService;
    private final QuestionnaireServiceImpl questionnaireService;
    private final CsvReader csvReader;
    private final MessageManagerImpl messageManager;

    private Student student;
    private Result result;

    @Autowired
    public ServiceCallImpl(LocaleServiceImpl localeService, QuestionnaireServiceImpl questionnaireService, CsvReader csvReader, MessageManagerImpl messageManager) {
        this.localeService = localeService;
        this.questionnaireService = questionnaireService;
        this.csvReader = csvReader;
        this.messageManager = messageManager;
    }

    public void setLanguage() throws WrongInputsError {
        localeService.setLocalization();
    }

    public void setLanguage(String abbreviationLanguage) throws WrongInputsError {
        localeService.setLocalization(abbreviationLanguage);
    }

    public void requestStudentName(){
        student = questionnaireService.requestStudentName();
    }

    public void startTest() throws CsvReaderException {
        ArrayList<Questionnaire> quizArray = csvReader.readCsv();
        result = questionnaireService.startQuestionnaire(quizArray);
    }

    public Student getStudent() {
        return student;
    }

    public Result getResult() {
        return result;
    }
}
