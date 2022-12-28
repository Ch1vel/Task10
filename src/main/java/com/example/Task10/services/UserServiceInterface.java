package com.example.Task10.services;

import com.example.Task10.models.User;

import java.util.List;

public interface UserServiceInterface {
    void save(User user);
    List<User> getAllUser();
    void removeUser(int id);
    User findUser(int id);
    void editUser(User user);
}
