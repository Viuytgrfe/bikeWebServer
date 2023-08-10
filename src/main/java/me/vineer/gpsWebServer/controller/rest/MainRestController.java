package me.vineer.gpsWebServer.controller.rest;

import me.vineer.gpsWebServer.database.model.Position;
import me.vineer.gpsWebServer.database.model.Session;
import me.vineer.gpsWebServer.database.repos.SessionRepo;
import me.vineer.gpsWebServer.database.services.PositionService;
import me.vineer.gpsWebServer.database.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MainRestController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private SessionService sessionService;

    @GetMapping(value = "rest/lastPosition")
    public Position.PositionRest getCoords() {
        return positionService.getLastPosition().toJSON();
    }

    @PostMapping("rest/new/position")
    public boolean addPosition(Position position) {
        position.setSession(sessionService.getCurrentSession());
        return positionService.addPosition(position);
    }

    @PostMapping("rest/session/close")
    public void closeSession() {
        sessionService.closeCurrentSession();
    }

    @PostMapping("rest/session/new")
    public void newSession() {
        sessionService.getCurrentSession();
    }
}
