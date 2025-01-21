package com.example.forum.service;

import com.example.forum.model.User;
import com.example.forum.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Valida las credenciales del usuario.
     *
     * @param email    El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return El usuario si las credenciales son válidas, o null si no lo son.
     */
    public User validateCredentials(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user; // Credenciales válidas
        }

        return null; // Credenciales inválidas
    }
}
