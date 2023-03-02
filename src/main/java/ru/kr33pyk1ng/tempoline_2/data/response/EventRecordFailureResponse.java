package ru.kr33pyk1ng.tempoline_2.data.response;

import java.util.List;

/**
 * Ответ на добавление события, подразумевающий неудачу.
 */
public class EventRecordFailureResponse extends EventRecordAbstractResponse {

    private final List<String> errors;

    public EventRecordFailureResponse(List<String> errors) {
        super("failure");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

}
