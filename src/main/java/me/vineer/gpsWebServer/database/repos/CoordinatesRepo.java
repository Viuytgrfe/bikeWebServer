package me.vineer.gpsWebServer.database.repos;

import me.vineer.gpsWebServer.database.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinatesRepo extends JpaRepository<Position, Long> {
    Optional<Position> findFirstByOrderByTimestampDesc();
}
