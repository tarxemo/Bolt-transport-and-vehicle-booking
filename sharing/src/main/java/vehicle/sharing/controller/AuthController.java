package vehicle.sharing.controller;

// AuthController.java

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @GetMapping("/auth/access-denied")
    public String showAccessDenied() {
        return "error/403";
    }
}
