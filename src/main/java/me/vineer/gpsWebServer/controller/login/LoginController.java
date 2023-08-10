package me.vineer.gpsWebServer.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(ModelMap model) {
        return "login";
    }

    @PostMapping("/login")
    public void loginUser(ModelMap model) {

    }
}
