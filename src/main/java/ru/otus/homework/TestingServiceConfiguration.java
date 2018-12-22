package ru.otus.homework;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.otus.homework.beans.*;

@Configuration
public class TestingServiceConfiguration {
    @Bean
    public LocaleService localeService() {
        LocaleService localeService = new LocaleService();
        localeService.setMessageManager(messageManager());

        return localeService;
    }

    @Bean
    public MessageManager messageManager() {
        return new MessageManager();
    }

    @Bean
    public ScanStudent scanStudent() {
        ScanStudent scanStudent = new ScanStudent();
        scanStudent.setMessageManager(messageManager());

        return scanStudent;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("locale/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public CsvReader csvReader() {
        CsvReader csvReader = new CsvReader();
        csvReader.setMessageManager(messageManager());

        return csvReader;
    }

    @Bean
    public WordEndings wordEndings() {
        WordEndings wordEndings = new WordEndings();
        wordEndings.setMessageManager(messageManager());

        return wordEndings;
    }

    @Bean
    public FinalResults finalResults() {
        FinalResults finalResults = new FinalResults();
        finalResults.setMessageManager(messageManager());
        finalResults.setWordEndings(wordEndings());

        return finalResults;
    }
}
