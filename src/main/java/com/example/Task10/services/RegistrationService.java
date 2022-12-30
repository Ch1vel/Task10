package com.example.Task10.services;

import com.example.Task10.models.User;
import com.example.Task10.repositories.RoleRepository;
import com.example.Task10.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public boolean registration(User user) {
        Optional<User> userFromDB = userRepository.findByNickname(user.getNickname());
        if (userFromDB.isPresent()) return false;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.add(roleRepository.findById(1).get());
        userRepository.save(user);
        return true;
    }
}
