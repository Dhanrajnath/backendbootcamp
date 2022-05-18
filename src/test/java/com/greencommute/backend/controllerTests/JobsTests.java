package com.greencommute.backend.controllerTests;

import com.greencommute.backend.controller.JobsController;
import com.greencommute.backend.dto.JobsDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.exception.DataNotFoundException;
import com.greencommute.backend.mapper.JobMapper;
import com.greencommute.backend.service.JobServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobsTests {

    @Mock
    JobServiceImpl jobService;

    @Mock
    JobsController jobsController = new JobsController(jobService);

    @Autowired
    JobMapper jobMapper;

    @Test
    public void getJobByIdTest() {

        Jobs jobs = new Jobs(1,"a","b","c",null,null);
        JobsDto jobsDto = jobMapper.toJobsDto(jobs);
        ResponseEntity responseEntity = new ResponseEntity<>(jobsDto, HttpStatus.OK);
        Mockito.when(jobsController.getJobById(1)).thenReturn(responseEntity);

        Optional<Jobs> jobsOptional = Optional.of(new Jobs(1,"a","b","c",null,null));
        JobsDto jobsDto1 = jobMapper.toJobsDto(jobsOptional.get());
        ResponseEntity responseEntity1 = new ResponseEntity<>(jobsDto1,HttpStatus.OK);

        Assertions.assertEquals(responseEntity.getStatusCode(),responseEntity1.getStatusCode());


    }

//    @Test
//    public void getAllJobsTest() {
//        List<Jobs> jobsList = new ArrayList<>();
//        Jobs jobs = new Jobs(1,"a","b","c",null,null);
//        jobsList.add(jobs);
//
//    }

}
