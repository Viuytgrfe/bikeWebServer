package me.vineer.gpsWebServer.database.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Data
@Table(name = "sessions")
@Entity
@ToString
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "session", fetch = FetchType.EAGER)
    private List<Position> positions = new ArrayList<>();
    private LocalDateTime startTimeStamp;
    private LocalDateTime endTimeStamp;

    @PrePersist
    private void init() {
        startTimeStamp = LocalDateTime.now();
    }

    public List<Position.PositionRest> getJSONPositions() {
        return getPositions().stream().map(Position::toJSON).sorted(Comparator.comparing(Position.PositionRest::getPositionTimestamp)).toList();
    }

    public Session closeSession() {
        endTimeStamp = LocalDateTime.now();
        return this;
    }
}
