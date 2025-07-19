package vehicle.sharing.service;

// AuthService.java

import vehicle.sharing.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    User register(User user, String roleName);
    boolean checkPassword(User user, String password);
}
