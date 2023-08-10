package me.vineer.gpsWebServer.database.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "coordinates")
@NoArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session")
    private Session session;
    private LocalDateTime timestamp;
    private Double latitude;
    private Double longitude;
    @PrePersist
    private void init() {
        timestamp = LocalDateTime.now();
    }

    public Position(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.session = new Session();
    }

    public PositionRest toJSON() {
        return new PositionRest(this);
    }
    @Data
    public static class PositionRest {
        private Long id;
        private Long sessionId;
        private LocalDateTime startSessionTimeStamp;
        private LocalDateTime endSessionTimeStamp;
        private LocalDateTime positionTimestamp;
        private Double latitude;
        private Double longitude;
        public PositionRest(Position position) {
            id = position.getId();
            sessionId = position.getSession().getId();
            startSessionTimeStamp = position.getSession().getStartTimeStamp();
            endSessionTimeStamp = position.getSession().getEndTimeStamp();
            positionTimestamp = position.getTimestamp();
            latitude = position.getLatitude();
            longitude = position.getLongitude();
        }
    }
}
