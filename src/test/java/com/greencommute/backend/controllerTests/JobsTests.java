package com.greencommute.backend.controllerTests;

import com.greencommute.backend.controller.JobsController;
import com.greencommute.backend.dto.JobsDto;
import com.greencommute.backend.entity.Jobs;
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
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        Optional<Jobs> jobsOptional = Optional.of(job);
        JobsDto jobsDto = jobMapper.toJobsDto(job);
        ResponseEntity responseEntity = new ResponseEntity<>(jobsDto.getJobId(), HttpStatus.OK);

        Mockito.when(jobService.getJobById(1)).thenReturn(jobsOptional);
        Mockito.when(jobsController.getJobById(1)).thenReturn(responseEntity);
        Assertions.assertEquals(jobsOptional.get().getJobId(),responseEntity.getBody());
    }

}
