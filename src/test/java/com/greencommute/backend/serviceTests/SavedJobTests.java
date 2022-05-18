package com.greencommute.backend.serviceTests;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.repository.SavedJobsJpa;
import com.greencommute.backend.service.SavedJobService;
import com.greencommute.backend.service.SavedJobServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SavedJobTests {

    @Mock
    private SavedJobService savedJobService;

    @Mock
    private SavedJobsJpa savedJobsJpa;

    @BeforeEach
    void initUseCase(){
        savedJobService = new SavedJobServiceImpl(savedJobsJpa);
    }

    @Test
    public void saveJobTest() {
        User user = new User(1,"user",null);
        Jobs job =  new Jobs(1, "Software Engineer", "Developer", "Hyderabad", null, null);
        doNothing().when(savedJobService).saveToSavedJobs(user.getUserId(), job.getJobId());
        savedJobService.saveToSavedJobs(1,1);
        Mockito.verify(savedJobService).saveToSavedJobs(1,1);
    }

    @Test
    public void getSavedJobTest() {
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        List<Jobs> savedJob = new ArrayList<>();
        savedJob.add(job);
        Mockito.when(savedJobService.getSavedJobsByUserId(1)).thenReturn(savedJob);
        Assertions.assertEquals(savedJob, savedJobService.getSavedJobsByUserId(1));
        Mockito.verify(savedJobService).getSavedJobsByUserId(1);
    }

    @Test
    public void deleteSavedJobTest() {
        Jobs job = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        List<Jobs> savedJob = new ArrayList<>();
        savedJob.add(job);
        boolean res=true;
        Mockito.when(savedJobService.deleteSavedJobs(1,1)).thenReturn(res);
        savedJob.remove(0);
        Assertions.assertEquals(savedJob, savedJobService.getSavedJobsByUserId(1));
        boolean res2=false;
        Mockito.when(savedJobService.deleteSavedJobs(22,12)).thenReturn(res2);
        Assertions.assertEquals(false, savedJobService.deleteSavedJobs(1, 12));
    }

}
