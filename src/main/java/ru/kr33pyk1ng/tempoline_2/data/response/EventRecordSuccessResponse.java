package ru.kr33pyk1ng.tempoline_2.data.response;

/**
 * Ответ на добавление события, подразумевающий успех.
 */
public class EventRecordSuccessResponse extends EventRecordAbstractResponse {

    private final String message;

    public EventRecordSuccessResponse(String message) {
        super("success");
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
