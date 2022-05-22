package com.greencommute.backend.serviceTests;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.SavedJobs;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.repository.SavedJobsJpa;
import com.greencommute.backend.repository.UserJpa;
import com.greencommute.backend.service.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SavedJobTests {

    @Autowired
    @InjectMocks
    private SavedJobServiceImpl savedJobService;

    @MockBean
    SavedJobsJpa savedJobsJpa;

    @Mock
    UserJpa userJpa;

    @BeforeEach
    void initUseCase(){
        savedJobService = new SavedJobServiceImpl(userJpa,savedJobsJpa);
    }


//    @Test
//    public void saveJobTest() {
//        User user = new User(1,"user",null);
//        Jobs job =  new Jobs(1, "Software Engineer", "Developer", "Hyderabad", null, null);
//
//        SavedJobs savedJobs = new SavedJobs(new Timestamp(System.currentTimeMillis()), user, job);
//        Mockito.when(savedJobsJpa.save(savedJobs)).thenReturn(savedJobs);
//        savedJobService.saveToSavedJobs(user,job);
//
//    }

//    @Test
//    public void getSavedJobTest() {
//        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
//        List<Jobs> savedJob = new ArrayList<>();
//        savedJob.add(job);
//        Mockito.when(savedJobService.getSavedJobsByUserId(1)).thenReturn(savedJob);
//        Assertions.assertEquals(savedJob, savedJobService.getSavedJobsByUserId(1));
//        Mockito.verify(savedJobService).getSavedJobsByUserId(1);
//    }

    @Test
    public void deleteSavedJobTest() {
        User user = new User(1,"user",null);
        Jobs job =  new Jobs(1, "Software Engineer", "Developer", "Hyderabad", null, null);
        SavedJobs savedJobs = new SavedJobs(new Timestamp(System.currentTimeMillis()), user, job);
        System.out.println(savedJobs);
        Mockito.when(savedJobsJpa.deleteByUserAndJobId(1,1)).thenReturn(savedJobs);
        doNothing().when(savedJobsJpa).delete(savedJobs);
        savedJobService.deleteSavedJobs(1,1);
        Mockito.verify(savedJobsJpa).delete(savedJobs);
    }

}