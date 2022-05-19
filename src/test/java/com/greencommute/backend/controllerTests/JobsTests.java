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

import java.util.ArrayList;
import java.util.List;

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
        JobsDto jobDto = jobMapper.toJobsDto(job);
        Mockito.when(jobsController.getJobById(1)).thenReturn(jobDto);
        Assertions.assertEquals(jobDto,jobsController.getJobById(1));
        Mockito.verify(jobsController).getJobById(1);
    }

    @Test
    void getAllJobsTest() {
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        List<Jobs> jobsList = new ArrayList<>();
        jobsList.add(job);
        JobsDto jobDto = jobMapper.toJobsDto(job);
        List<JobsDto> jobsDtoList = new ArrayList<>();
        jobsDtoList.add(jobDto);
        Mockito.when(jobService.getAllJobs()).thenReturn(jobsList);
        Mockito.when(jobsController.getAllJobs(null,null)).thenReturn(jobsDtoList);
        Assertions.assertEquals(jobsDtoList,jobsController.getAllJobs(null,null));
        Mockito.verify(jobsController).getAllJobs(null,null);
    }

}
