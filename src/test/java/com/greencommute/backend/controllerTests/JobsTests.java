package com.greencommute.backend.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greencommute.backend.controller.JobsController;
import com.greencommute.backend.dto.JobsDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.mapper.JobMapper;
import com.greencommute.backend.service.JobService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
class JobsTests {

    @Mock
    JobService jobService;

    @InjectMocks
    JobsController jobsController;

    @Mock
    JobMapper jobMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(jobsController).build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void getJobTest() throws Exception {
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        JobsDto jobDto = jobMapper.toJobsDto(job);
        Optional<Jobs> jobsOptional = Optional.of(job);

        when(jobService.getJobById(1)).thenReturn(jobsOptional);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/jobs/1").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobDto))).
                andDo(MockMvcResultHandlers.print());
        verify(jobService).getJobById(1);
        verify(jobService,times(1)).getJobById(1);
    }

    @Test
    void getJobByIdTest(){
//        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
//        JobsDto jobDto = jobMapper.toJobsDto(job);
//        Optional<Jobs> jobsOptional = Optional.of(job);
//        when(jobService.getJobById(1)).thenReturn(jobsOptional);
//        Assertions.assertEquals(jobsOptional.get().getJobId(),jobsController.getJobById(1).getJobId());

//        Mockito.when(jobsController.getJobById(1)).thenReturn(jobDto);
//        Assertions.assertEquals(jobDto,jobsController.getJobById(1));
//        Mockito.verify(jobsController).getJobById(1);
    }

    @Test
    void getAllJobsTest() throws Exception {
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        JobsDto jobDto = jobMapper.toJobsDto(job);
        Optional<Jobs> jobsOptional = Optional.of(job);
        List<Jobs> jobsList = new ArrayList<>();
        jobsList.add(job);
        List<JobsDto> jobsDtoList = jobMapper.toJobDtoList(jobsList);
        when(jobService.getAllJobs()).thenReturn(jobsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/jobs").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(jobsDtoList))).
                andDo(MockMvcResultHandlers.print());
        verify(jobService).getAllJobs();
        verify(jobService,times(1)).getAllJobs();
//        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
//        List<Jobs> jobsList = new ArrayList<>();
//        jobsList.add(job);
//        JobsDto jobDto = jobMapper.toJobsDto(job);
//        List<JobsDto> jobsDtoList = new ArrayList<>();
//        jobsDtoList.add(jobDto);
//        Mockito.when(jobService.getAllJobs()).thenReturn(jobsList);
//        Mockito.when(jobsController.getAllJobs(null,null)).thenReturn(jobsDtoList);
//        Assertions.assertEquals(jobsDtoList,jobsController.getAllJobs(null,null));
//        Mockito.verify(jobsController).getAllJobs(null,null);
    }

}
