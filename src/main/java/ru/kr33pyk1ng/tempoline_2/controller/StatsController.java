package ru.kr33pyk1ng.tempoline_2.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kr33pyk1ng.tempoline_2.data.EventRecord;
import ru.kr33pyk1ng.tempoline_2.data.EventRecordDto;
import ru.kr33pyk1ng.tempoline_2.data.response.EventRecordFailureResponse;
import ru.kr33pyk1ng.tempoline_2.data.response.EventRecordSuccessResponse;
import ru.kr33pyk1ng.tempoline_2.exceptions.BadEventRecordException;
import ru.kr33pyk1ng.tempoline_2.services.EventRecordService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final EventRecordService eventRecordService;

    @Autowired
    public StatsController(EventRecordService eventRecordService) {
        this.eventRecordService = eventRecordService;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addNewEventRecord(@Valid EventRecordDto eventRecordDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // если есть ошибки - собираем в список и кидаем эксепшен.
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> {
                if (error instanceof FieldError) {
                    errors.add("поле " + ((FieldError) error).getField() + ": " + error.getDefaultMessage());
                } else {
                    errors.add(error.getDefaultMessage());
                }
            });
            throw new BadEventRecordException(errors);
        }
        EventRecord.Type type;
        try {
            // проверяем существование указанного типа.
            type = EventRecord.Type.valueOf(eventRecordDto.getType());
        } catch (IllegalArgumentException ignored) {
            throw new BadEventRecordException(Collections.singletonList("Указан неизвестный тип события!"));
        }
        this.eventRecordService.addNewEventRecordByDto(eventRecordDto);
        return ResponseEntity.ok(new EventRecordSuccessResponse("Новая запись о событии добавлена в БД."));
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEventRecords(@RequestParam(required = false) String type,
                                                @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                @RequestParam(required = false, defaultValue = "7") Integer limit) {
        // offset и limit нельзя указать меньше нуля.
        if (offset == null || offset < 0)
            offset = 0;
        if (limit == null || limit < 0)
            limit = 0;
        return ResponseEntity.ok(eventRecordService.getEventRecordsGroupedByDay(type, offset, limit));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleBadEventRecord(BadEventRecordException badEventRecordException) {
        return ResponseEntity.badRequest().body(new EventRecordFailureResponse(badEventRecordException.getErrors()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleOtherExceptions(Exception exception) {
        return ResponseEntity.internalServerError().body(new EventRecordFailureResponse(Collections.singletonList(exception.getMessage())));
    }

}
