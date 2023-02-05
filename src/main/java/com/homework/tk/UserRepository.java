package com.homework.tk;

import java.util.*;

public class UserRepository {
    private ArrayList<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> searchByLogin(String login) {
        return users.stream()
                .filter(e -> e.getLogin().equals(login))
                .findFirst();
    }

    public Optional<User> searchByLoginAndPassword(String login, String password) {
        return users.stream()
                .filter(e -> e.getLogin().equals(login) && e.getPassword().equals(password))
                .findFirst();
    }

    public User addUser(User user) {
            users.add(user);
            return user;
    }


}
