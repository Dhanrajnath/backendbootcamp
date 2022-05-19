package com.greencommute.backend.serviceTests;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.SavedJobs;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.repository.SavedJobsJpa;
import com.greencommute.backend.repository.UserJpa;
import com.greencommute.backend.service.SavedJobServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@SpringBootTest
class SavedJobsTests {

    @Mock
    SavedJobServiceImpl savedJobService;

    @Mock
    SavedJobsJpa savedJobsJpa;

    @Mock
    UserJpa userJpa;

    @BeforeEach
    void initUseCase() {
        savedJobService = new SavedJobServiceImpl(userJpa,savedJobsJpa);
    }

    Timestamp timestamp =new Timestamp(System.currentTimeMillis());

    @Test
    void deleteSavedJobTest(){
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        User user = new User(1, "Dhanrajnath", null);
        SavedJobs savedJobs = new SavedJobs(timestamp,user,job);

//        doNothing().when(savedJobService.deleteSavedJobs(1,1));
//        Mockito.when(savedJobService.deleteSavedJobs(1,1)).thenReturn(false);
//        Assertions.assertEquals(false,savedJobService.deleteSavedJobs(1,1));
//        Mockito.verify(savedJobService).deleteSavedJobs(1,1);
//        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
//        List<Jobs> savedJob = new ArrayList<>();
//        savedJob.add(job);
//        Mockito.when(savedJobService.getSavedJobsByUserId(1)).thenReturn(savedJob);
//        Assertions.assertEquals(savedJob, savedJobService.getSavedJobsByUserId(1));
//        Mockito.verify(savedJobService).getSavedJobsByUserId(1);

//        Jobs jobs = new Jobs(1,"a","b","c",null,null);
//        List<Jobs> jobsList = new ArrayList<>();
//        jobsList.add(jobs);
//        User user = new User(1,"user1",null);
//        SavedJobs savedJobs = new SavedJobs(timestamp,user,jobs);
//        List<SavedJobs> savedJobsList = new ArrayList<>();
//        savedJobsList.add(savedJobs);
//        Mockito.when(userJpa.findById(1)).thenReturn(Optional.of(user));

    }

}
