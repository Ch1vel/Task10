package com.example.Task10.services;

import com.example.Task10.models.User;
import com.example.Task10.repositories.UserRepository;
import com.example.Task10.security.TaskUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public TaskUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByNickname(username);

        if(optional.isEmpty()) throw new UsernameNotFoundException("Username not found");

        return new TaskUserDetails(optional.get());
    }
}
