package org.example.util;

import org.example.config.LocaleProvider;
import org.springframework.context.MessageSource;

public class LocalizationServiceImpl implements LocalizationService {

    public static final String QUESTIONS_TOTAL = "questions.total";
    public static final String QUESTIONS_DOT = "questions.dot";
    public static final String TEST_WELCOME = "test.welcome";
    public static final String TEST_LAST = "test.last";
    public static final String TEST_NUMBERS = "test.numbers";
    public static final String TEST_SIMPLE = "test.simple";
    public static final String TEST_PARSE_ERR = "test.parse.err";
    public static final String TEST_SCORE = "test.score";
    public static final String TEST_EXCELLENT = "test.excellent";
    public static final String TEST_GOOD = "test.good";

    private final LocaleProvider localeProvider;
    private final MessageSource messageSource;

    public LocalizationServiceImpl(LocaleProvider localeProvider, MessageSource messageSource) {
        this.localeProvider = localeProvider;
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, localeProvider.getCurrent());
    }
}
