package me.vineer.gpsWebServer.controller.main;

import me.vineer.gpsWebServer.database.model.Position;
import me.vineer.gpsWebServer.database.model.Session;
import me.vineer.gpsWebServer.database.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    public SessionService sessionService;

    @GetMapping("/")
    public String getMainPage() {
        return "navigation";
    }

    @GetMapping("/lastPosition")
    public String getLastPositionPage() {
        return "lastPosition";
    }

    @GetMapping("/sessions")
    public String getSessions(Model model) {
        model.addAttribute("sessions", sessionService.getSessions());
        return "sessions";
    }

    @GetMapping("/sessions/session/{id}")
    public String getSessionById(@PathVariable Long id, Model model) {
        model.addAttribute("sess", sessionService.getSessionById(id));
        return "session";
    }

    public static String formatDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Duration duration = Duration.between(startDateTime, endDateTime);
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        String daysString = formatQuantity("день", "дня", "дней", days) + (days > 0 ? " " : "");
        String hoursString = formatQuantity("час", "часа", "часов", hours) + (hours > 0 ? " " : "");
        String minutesString = formatQuantity("минута", "минуты", "минут", minutes) + (minutes > 0 ? " " : "");
        String secondsString = formatQuantity("секунда", "секунды", "секунд", seconds);
        StringBuilder builder = new StringBuilder();
        if (days > 0) {
            builder.append(daysString);
        }
        if (hours > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(hoursString);
        }
        if (minutes > 0 || builder.length() == 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(minutesString);
            builder.append(secondsString);
        }
        return builder.toString();
    }

    private static String formatQuantity(String one, String few, String many, long quantity) {
        if (quantity == 0) {
            return "";
        }
        if (quantity % 10 == 1 && quantity % 100 != 11) {
            return quantity + " " + one;
        }
        if (quantity % 10 >= 2 && quantity % 10 <= 4 && (quantity % 100 < 10 || quantity % 100 >= 20)) {
            return quantity + " " + few;
        }
        return quantity + " " + many;
    }
}
