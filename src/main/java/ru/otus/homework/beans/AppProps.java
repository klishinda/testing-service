package ru.otus.homework.beans;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "lang")
@Component
@Data
public class AppProps {
    private String allLangs;
    private Map<String, String> langLocalization;
}