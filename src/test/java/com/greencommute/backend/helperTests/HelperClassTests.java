package com.greencommute.backend.helperTests;

import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.Skills;
import com.greencommute.backend.helper.Helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HelperClassTests {

    @Mock
    Helper helper;

    @Test
    void getSkillsTest(){
        List<Skills> skillList = new ArrayList<>();
        Skills skill = new Skills(1,"a",null);
        skillList.add(skill);
        List<String> skillNames = new ArrayList<>();
        skillNames.add("a");
        Mockito.when(helper.getSkills(skillList)).thenReturn(skillNames);
        Assertions.assertEquals(skillNames,helper.getSkills(skillList));
        Mockito.verify(helper).getSkills(skillList);
    }

    @Test
    void getJobsSearchBySkillsTest() {
        List<Jobs> jobsList = new ArrayList<>();
        List<Jobs> jobsList1 = new ArrayList<>();
        Jobs job1 = new Jobs(1,"Software Engineer","Developer","Hyderabad",null,null);
        Jobs job2 = new Jobs(2,"Software Engineer","Developer","Hyderabad",null,null);
        List<Skills> skillList = new ArrayList<>();
        Skills skill = new Skills(1,"a",null);
        skillList.add(skill);
        job1.setSkillList(skillList);
        job2.setSkillList(skillList);
        jobsList.add(job1);
        jobsList.add(job2);

        String[] skillSearch = new String[]{"a"};
        Mockito.when(helper.getJobsSearchBySkills(jobsList,skillSearch)).thenReturn(jobsList);
        Assertions.assertEquals(jobsList,helper.getJobsSearchBySkills(jobsList,skillSearch));
        Mockito.verify(helper).getJobsSearchBySkills(jobsList,skillSearch);

        String[] skillSearch2 = new String[]{"abb"};
        Mockito.when(helper.getJobsSearchBySkills(jobsList,skillSearch2)).thenReturn(jobsList1);
        Assertions.assertEquals(jobsList1,helper.getJobsSearchBySkills(jobsList,skillSearch2));
        Mockito.verify(helper).getJobsSearchBySkills(jobsList,skillSearch2);
    }
}
