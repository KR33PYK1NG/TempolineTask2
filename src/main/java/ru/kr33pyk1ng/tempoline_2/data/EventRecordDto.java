package ru.kr33pyk1ng.tempoline_2.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO-прослойка для записи события.<br>
 * Используется для валидации входящих значений.
 */
public class EventRecordDto {

    @NotNull(message = "Сообщение события не должно быть null!")
    @Size(max = 100, message = "Сообщение события не должно быть больше 100 символов!")
    private String message;

    @NotNull(message = "Тип события не должен быть null!")
    private String type;

    public EventRecordDto(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public EventRecordDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
