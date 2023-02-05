package com.homework.tk;

import com.homework.tk.exceptions.UserNonUniqueException;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllLogins() {
        return userRepository.getAllUsers().stream().map(e -> e.getLogin()).collect(Collectors.toList());
    }


    public void createUser(String login, String password) {
        if (login != null && !(login.isBlank()) && password != null && !(password.isBlank())) {
            if (userRepository.getAllUsers().stream().noneMatch(e -> e.getLogin().equals(login))) {
                userRepository.addUser(new User(login, password));
            } else {
                throw new UserNonUniqueException("Пользователь с таким логином уже присутствует в базе");
            }
        } else {
            throw new IllegalArgumentException("Данные не должны быть пустыми");
        }
    }


}
