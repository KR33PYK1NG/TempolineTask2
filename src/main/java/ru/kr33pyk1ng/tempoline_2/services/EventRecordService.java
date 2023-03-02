package ru.kr33pyk1ng.tempoline_2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kr33pyk1ng.tempoline_2.data.EventRecord;
import ru.kr33pyk1ng.tempoline_2.data.EventRecordDto;
import ru.kr33pyk1ng.tempoline_2.repositories.EventRecordRepository;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class EventRecordService {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final EventRecordRepository eventRecordRepository;

    @Autowired
    public EventRecordService(EventRecordRepository eventRecordRepository) {
        this.eventRecordRepository = eventRecordRepository;
    }

    @Transactional
    public void addNewEventRecordByDto(EventRecordDto eventRecordDto) {
        this.eventRecordRepository.save(new EventRecord(new Date(), EventRecord.Type.valueOf(eventRecordDto.getType()), eventRecordDto.getMessage()));
    }

    public Map<String, List<EventRecord>> getEventRecordsGroupedByDay(String type, int offset, int limit) {
        // сдвигаем нижнюю границу глубже в историю.
        Calendar lowerStampCalendar = Calendar.getInstance();
        lowerStampCalendar.add(Calendar.DAY_OF_MONTH, -(limit + offset));
        resetHoursMinsSecs(lowerStampCalendar);
        // сдвигаем верхнюю границу глубже в историю.
        Calendar upperStampCalendar = Calendar.getInstance();
        upperStampCalendar.add(Calendar.DAY_OF_MONTH, -offset + 1);
        resetHoursMinsSecs(upperStampCalendar);
        List<EventRecord> eventRecordsInRange;
        // получаем (не)типизированные события в промежутке lower->upper.
        if (type == null) {
            eventRecordsInRange = this.eventRecordRepository.getEventRecordsDuringTimeframe(lowerStampCalendar.getTime(), upperStampCalendar.getTime());
        } else {
            eventRecordsInRange = this.eventRecordRepository.getTypedEventRecordsDuringTimeframe(EventRecord.Type.valueOf(type), lowerStampCalendar.getTime(), upperStampCalendar.getTime());
        }
        // LinkedHashMap здесь полезно для сохранения порядка вставки.
        Map<String, List<EventRecord>> output = new LinkedHashMap<>();
        // заполняем мапу output пустыми днями в промежутке между offset и limit.
        // это позволит получить в теле запроса красивый ответ без пробелов в череде дней.
        int limitTemp = 0;
        while (limitTemp != limit) {
            Calendar temp = Calendar.getInstance();
            temp.add(Calendar.DAY_OF_MONTH, -(limitTemp + offset));
            output.put(FORMAT.format(temp.getTime()), new ArrayList<>());
            limitTemp++;
        }
        eventRecordsInRange.forEach(eventRecord -> {
            List<EventRecord> recordsList = output.computeIfAbsent(FORMAT.format(eventRecord.getTimestamp()), k -> new ArrayList<>());
            recordsList.add(eventRecord);
        });
        return output;
    }

    private void resetHoursMinsSecs(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

}
