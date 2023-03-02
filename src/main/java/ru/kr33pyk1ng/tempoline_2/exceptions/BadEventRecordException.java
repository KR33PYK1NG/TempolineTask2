package ru.kr33pyk1ng.tempoline_2.exceptions;

import java.util.List;

/**
 * Ошибка, кидаемая при неудачной обработке входного JSON.
 */
public class BadEventRecordException extends RuntimeException {

    private final List<String> errors;

    public BadEventRecordException(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

}
