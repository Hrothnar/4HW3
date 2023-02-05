package com.homework.tk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {
    private final UserRepository userRepository = new UserRepository();

    @Test
    public void emptyList() {
        Assertions.assertArrayEquals(userRepository.getAllUsers().toArray(), new User[]{});
    }

    @Test
    public void completedList() {
        User user1 = new User("peach", "peach555");
        User user2 = new User("watermelon", "watermelon777");
        User user3 = new User("strawberry", "strawberry999");
        User[] users = {user1, user2, user3};

        User user4 = new User("peach", "peach555");
        User user5 = new User("watermelon", "watermelon777");
        User user6 = new User("strawberry", "strawberry999");
        userRepository.addUser(user4);
        userRepository.addUser(user5);
        userRepository.addUser(user6);
        Assertions.assertArrayEquals(userRepository.getAllUsers().toArray(), users);
    }

    @Test
    public void userWithLoginExist() {
        userRepository.addUser(new User("peach", "peach555"));
        Assertions.assertNotNull(userRepository.searchByLogin("peach").orElse(null));
    }

    @Test
    public void userWithLoginDoesNotExist() {
        userRepository.addUser(new User("watermelon", "watermelon777"));
        Assertions.assertNull(userRepository.searchByLogin("peach").orElse(null));
    }

    @Test
    public void userWithLoginAndPasswordExist() {
        userRepository.addUser(new User("strawberry", "strawberry999"));
        Assertions.assertNotNull(userRepository.searchByLoginAndPassword("strawberry", "strawberry999").orElse(null));
    }

    @Test
    public void userWithLoginAndPasswordDoesNotExist() {
        userRepository.addUser(new User("watermelon", "watermelon777"));
        Assertions.assertNull(userRepository.searchByLoginAndPassword("strawberry", "strawberry999").orElse(null));
    }

    @Test
    public void userWithLoginAndPasswordDoesNotExistTwo() {
        userRepository.addUser(new User("watermelon", "watermelon777"));
        userRepository.addUser(new User("strawberry", "strawberry999"));
        Assertions.assertNull(userRepository.searchByLoginAndPassword("peach", "strawberry999").orElse(null));
    }

    @Test
    public void userWithLoginAndPasswordDoesNotExistThree() {
        userRepository.addUser(new User("watermelon", "watermelon777"));
        userRepository.addUser(new User("strawberry", "strawberry999"));
        Assertions.assertNull(userRepository.searchByLoginAndPassword("watermelon", "peach555").orElse(null));
    }


}