package vehicle.sharing.service.impl;

// AuthServiceImpl.java

import vehicle.sharing.model.Role;
import vehicle.sharing.model.User;
import vehicle.sharing.repository.UserRepository;
import vehicle.sharing.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(User user, String roleName) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.REQUESTER);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
