package me.vineer.gpsWebServer.database.services;

import me.vineer.gpsWebServer.database.model.Session;
import me.vineer.gpsWebServer.database.repos.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    @Autowired
    private SessionRepo sessionRepo;


    private Session createNewSession() {
        Session session = new Session();
        sessionRepo.save(session);
        return session;
    }

    public void saveSession(Session session) {
        sessionRepo.save(session);
    }

    public Session getCurrentSession() {
        Session session = sessionRepo.findFirstByEndTimeStampIsNullOrderByStartTimeStampAsc().orElse(null);
        if(session == null) session = createNewSession();
        return session;
    }

    public void closeCurrentSession() {
        sessionRepo.findFirstByEndTimeStampIsNullOrderByStartTimeStampAsc().ifPresent(save -> sessionRepo.save(save.closeSession()));
    }

    public List<Session> getSessions() {
        return sessionRepo.findAll(Sort.by(Sort.Direction.DESC, "startTimeStamp"));
    }

    public Session getSessionById(Long id) {
        return sessionRepo.findById(id).orElse(null);
    }
}
