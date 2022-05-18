package com.greencommute.backend.serviceTests;

import com.greencommute.backend.entity.User;
import com.greencommute.backend.repository.UserJpa;
import com.greencommute.backend.service.UserService;
import com.greencommute.backend.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class UserTests {

    @Mock
    private UserService userService;

    @Mock
    private UserJpa userJpa;

    @BeforeEach
    void initUseCase(){
        userService = new UserServiceImpl(userJpa);
    }

    @Test
    void getUserByIdTest() {
        Optional<User> user = Optional.of(new User(1, "Dhanrajnath", null));
        Mockito.when(userJpa.findById(1)).thenReturn(user);
        Assertions.assertEquals(user,userService.getUserById(1));
        Mockito.verify(userJpa).findById(1);
    }
}