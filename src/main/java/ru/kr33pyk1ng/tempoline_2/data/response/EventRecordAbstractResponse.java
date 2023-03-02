package ru.kr33pyk1ng.tempoline_2.data.response;

/**
 * Абстрактный ответ на добавление события.
 */
public abstract class EventRecordAbstractResponse {

    private final String responseType;

    public EventRecordAbstractResponse(String responseType) {
        this.responseType = responseType;
    }

    public String getResponseType() {
        return responseType;
    }

}
