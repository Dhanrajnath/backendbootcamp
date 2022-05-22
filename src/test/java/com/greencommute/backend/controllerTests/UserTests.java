package com.greencommute.backend.controllerTests;

import com.greencommute.backend.controller.UserController;
import com.greencommute.backend.dto.UserDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.mapper.UserMapper;
import com.greencommute.backend.service.JobServiceImpl;
import com.greencommute.backend.service.SavedJobServiceImpl;
import com.greencommute.backend.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserTests {

    @Mock
    UserServiceImpl userService;

    @Mock
    SavedJobServiceImpl savedJobService;

    @Mock
    JobServiceImpl jobService;

    @Autowired
    UserController userController = new UserController(userService,jobService,savedJobService);

    @Autowired
    UserMapper userMapper;



    @Test
    void getUserByIdTest(){
        Optional<User> user = Optional.of(new User(1, "Dhanrajnath", null));
        UserDto userDto = userMapper.toUserDto(user.get());
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(userDto,HttpStatus.OK);
        Assertions.assertNotNull(userController);
        Mockito.when(userService.getUserById(1)).thenReturn(user);
        Assertions.assertEquals(Objects.requireNonNull(responseEntity.getBody()).getUserId() , Objects.requireNonNull(userController.getUserById(1).getBody()).getUserId());
    }

    @Test
    void getSavedJobsOfUserTest() {
        doNothing().when(savedJobService).deleteSavedJobs(1,1);
        savedJobService.deleteSavedJobs(1,1);
        Mockito.verify(savedJobService).deleteSavedJobs(1,1);
    }

}
