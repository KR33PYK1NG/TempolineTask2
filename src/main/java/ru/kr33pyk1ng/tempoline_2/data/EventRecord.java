package ru.kr33pyk1ng.tempoline_2.data;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Запись произошедшего события.<br>
 * Таблица all_events.
 */
@Entity
@Table(name = "all_events")
public class EventRecord {

    public enum Type {
        JOIN, QUIT, LOGIN, REGISTER, PLAY, STOP, OTHER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String message;

    public EventRecord(Date timestamp, Type type, String message) {
        this.timestamp = timestamp;
        this.type = type;
        this.message = message;
    }

    public EventRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
