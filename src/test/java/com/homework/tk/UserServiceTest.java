package com.homework.tk;

import com.homework.tk.exceptions.UserNonUniqueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    private final User user1 = new User("peach", "peach555");
    private final User user2 = new User("watermelon", "watermelon777");


    @Test
    public void emptyLoginsList() {
        Assertions.assertArrayEquals(userService.getAllLogins().toArray(), new String[]{});
    }

    @Test
    public void completedLoginsList() {
        Mockito.when(userRepository.getAllUsers()).thenReturn(new ArrayList<User>(List.of(user1, user2)));
        String[] logins = {"peach", "watermelon"};
        Assertions.assertArrayEquals(userService.getAllLogins().toArray(), logins);
    }

    @Test
    public void createUserWithCorrectLoginAndPassword() {
        userService.createUser("strawberry", "strawberry555");
        Mockito.verify(userRepository).addUser(Mockito.any(User.class));
    }

    @Test
    public void createUserWithBlankLogin() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.createUser("", "strawberry555"));
    }

    @Test
    public void createUserWithBlankPassword() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.createUser("strawberry", null));
    }

    @Test
    public void createUserWithBlankLoginAndPassword() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.createUser("", null));
    }

    @Test
    public void createUserWithTheSameLogin() {
        Mockito.when(userRepository.getAllUsers()).thenReturn(new ArrayList<User>(List.of(user1, user2)));
        Assertions.assertThrows(UserNonUniqueException.class, () -> userService.createUser("peach", "peach555"));
    }


}