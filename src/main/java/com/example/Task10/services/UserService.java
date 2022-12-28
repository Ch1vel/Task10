package com.example.Task10.services;


import com.example.Task10.models.User;
import com.example.Task10.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserService implements UserServiceInterface{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Transactional
    public void removeUser(int id) {
        userRepository.deleteById(id);
    }

    public User findUser(int id) {
       return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void editUser(User user) {
        userRepository.save(user);
    }
}
