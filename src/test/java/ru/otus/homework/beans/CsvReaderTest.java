package ru.otus.homework.beans;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import ru.otus.homework.exceptions.CsvReaderException;
import ru.otus.homework.model.Questionnaire;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {

    private static final String CSV_FILE_PATH = "src/main/resources/questions_test.csv";

    @Test
    void readCsv() {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader)
        ) {
            ArrayList<Questionnaire> quizArray = new ArrayList();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord[0].isEmpty() && nextRecord[1].isEmpty()) {
                    throw new CsvReaderException("В файле обнаружены пустые строки!");
                }
                else if (nextRecord[0].isEmpty()) {
                    throw new CsvReaderException("В файле присутствуют ответы без вопросов!");
                }
                else if (nextRecord[1].isEmpty()) {
                    throw new CsvReaderException("В файле присутствуют вопросы без ответов!");
                }

                quizArray.add(new Questionnaire(nextRecord[0], nextRecord[1]));
            }

        } catch (CsvReaderException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}