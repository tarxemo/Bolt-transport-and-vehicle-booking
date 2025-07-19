package vehicle.sharing.service;

// UserService.java

import vehicle.sharing.model.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    void deleteUser(Long id);
    boolean usernameExists(String username);
    boolean emailExists(String email);
}

