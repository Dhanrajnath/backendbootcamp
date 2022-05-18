package com.greencommute.backend.controllerTests;

import com.greencommute.backend.controller.UserController;
import com.greencommute.backend.dto.UserDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.mapper.UserMapper;
import com.greencommute.backend.service.SavedJobServiceImpl;
import com.greencommute.backend.service.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @Mock
    UserServiceImpl userService;

    @Mock
    SavedJobServiceImpl savedJobService;

    @Mock
    UserController userController = new UserController(userService,savedJobService);

    @Mock
    UserMapper userMapper;



    @Test
    public void getUserByIdTest(){
        User user = new User(1, "Dhanrajnath", null);
        UserDto userDto = userMapper.toUserDto(user);
        ResponseEntity responseEntity = new ResponseEntity<>(userDto,HttpStatus.OK);
        Mockito.when(userController.getUserById(1)).thenReturn(responseEntity);
        Assertions.assertEquals(responseEntity,userController.getUserById(1));
        Mockito.verify(userController).getUserById(1);
    }

    @Test
    public void getUserSavedJobsByIdTest(){
        List<Jobs> jobsList = new ArrayList<>();
        Mockito.when(savedJobService.getSavedJobsByUserId(1)).thenReturn(jobsList);
        Assertions.assertEquals(jobsList ,userController.getSavedJobsOfUser(1));
        Mockito.verify(userController).getSavedJobsOfUser(1);
    }
}
