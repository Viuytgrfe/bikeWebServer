package me.vineer.gpsWebServer.database.repos;

import me.vineer.gpsWebServer.database.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {
    Optional<Session> findFirstByEndTimeStampIsNullOrderByStartTimeStampAsc();
}
