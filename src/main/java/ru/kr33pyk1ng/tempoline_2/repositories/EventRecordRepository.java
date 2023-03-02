package ru.kr33pyk1ng.tempoline_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kr33pyk1ng.tempoline_2.data.EventRecord;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRecordRepository extends JpaRepository<EventRecord, Integer> {

    @Query("SELECT record FROM EventRecord record WHERE record.timestamp >= :lowerStamp AND record.timestamp < :upperStamp ORDER BY record.timestamp DESC")
    List<EventRecord> getEventRecordsDuringTimeframe(Date lowerStamp, Date upperStamp);

    @Query("SELECT record FROM EventRecord record WHERE record.type = :type AND record.timestamp >= :lowerStamp AND record.timestamp < :upperStamp ORDER BY record.timestamp DESC")
    List<EventRecord> getTypedEventRecordsDuringTimeframe(EventRecord.Type type, Date lowerStamp, Date upperStamp);

}
