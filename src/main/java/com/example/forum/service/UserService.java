package com.example.forum.service;

import com.example.forum.model.Role;
import com.example.forum.model.User;
import com.example.forum.repository.RoleRepository;
import com.example.forum.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Valida las credenciales del usuario.
     *
     * @param email    El correo electrónico del usuario.
     * @param rawPassword La contraseña del usuario.
     * @return El usuario si las credenciales son válidas, o null si no lo son.
     */
    public User validateCredentials(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User registerUser(String email, String password, Set<String> roles) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        Set<Role> userRoles = roles.stream()
                .map(roleRepository::findByName)
                .collect(Collectors.toSet());

        user.setRoles(userRoles);
        return userRepository.save(user);
    }

}
