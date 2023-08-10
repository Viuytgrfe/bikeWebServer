package me.vineer.gpsWebServer.database.services;

import me.vineer.gpsWebServer.database.model.Position;
import me.vineer.gpsWebServer.database.model.Session;
import me.vineer.gpsWebServer.database.repos.CoordinatesRepo;
import me.vineer.gpsWebServer.database.repos.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {
    @Autowired
    private CoordinatesRepo coordinatesRepo;


    public Position getLastPosition() {
        return coordinatesRepo.findFirstByOrderByTimestampDesc().orElse(new Position(0.0, 0.0));
    }

    public boolean addPosition(Position position) {
        coordinatesRepo.save(position);
        return true;
    }
}
