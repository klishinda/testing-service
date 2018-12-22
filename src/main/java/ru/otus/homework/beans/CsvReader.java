package ru.otus.homework.beans;

import au.com.bytecode.opencsv.CSVReader;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.homework.exceptions.CsvReaderException;
import ru.otus.homework.model.Questionnaire;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@Setter
public class CsvReader {

    @Autowired
    private MessageManager messageManager;

    public ArrayList<Questionnaire> readCsv() throws CsvReaderException {
        final String CSV_FILE_PATH = messageManager.getMessage("csv_reader.path_to_csv");

        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader)
        ) {
            ArrayList<Questionnaire> quizArray = new ArrayList();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord[0].isEmpty() && nextRecord[1].isEmpty()) {
                    throw new CsvReaderException(messageManager.getMessage("csv_reader.exceptions.empty_strings"));
                }
                else if (nextRecord[0].isEmpty()) {
                    throw new CsvReaderException(messageManager.getMessage("csv_reader.exceptions.empty_questions"));
                }
                else if (nextRecord[1].isEmpty()) {
                    throw new CsvReaderException(messageManager.getMessage("csv_reader.exceptions.empty_answers"));
                }
                quizArray.add(new Questionnaire(nextRecord[0], nextRecord[1]));
            }
            return quizArray;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
