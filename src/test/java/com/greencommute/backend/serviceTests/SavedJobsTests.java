package com.greencommute.backend.serviceTests;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.SavedJobs;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.repository.SavedJobsJpa;
import com.greencommute.backend.repository.UserJpa;
import com.greencommute.backend.service.SavedJobServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

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
    }

}
