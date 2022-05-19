package com.greencommute.backend.controllerTests;

import com.greencommute.backend.controller.JobsController;
import com.greencommute.backend.dto.JobsDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.mapper.JobMapper;
import com.greencommute.backend.service.JobServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class JobsTests {

    @Mock
    JobServiceImpl jobService;

    @Mock
    JobsController jobsController = new JobsController(jobService);

    @Mock
    JobMapper jobMapper;


    @Test
    void getJobByIdTest(){
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        int id = 1;
        JobsDto jobDto = jobMapper.toJobsDto(job);
        Mockito.when(jobsController.getJobById(id)).thenReturn(jobDto);
        Assertions.assertEquals(jobDto,jobsController.getJobById(id));
        Mockito.verify(jobsController).getJobById(id);
    }

}
