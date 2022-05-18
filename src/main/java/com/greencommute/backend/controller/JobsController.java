package com.greencommute.backend.controller;


import com.greencommute.backend.dto.JobsDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.exception.DataNotFoundException;
import com.greencommute.backend.helper.Helper;
import com.greencommute.backend.mapper.JobMapper;
import com.greencommute.backend.service.JobServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/jobs")
public class JobsController {

    private final JobServiceImpl jobService;

    @Autowired
    public JobsController(JobServiceImpl jobService) {
        this.jobService = jobService;
    }

    @Autowired
    public Helper helper;

    @Autowired
    public JobMapper jobMapper;

    @GetMapping
    public ResponseEntity<List<JobsDto>> getAllJobs(@RequestParam(value="location",required = false) String loc, @RequestParam(value="skill",required = false) String[] skill) {
        List<Jobs> jobsList = jobService.getAllJobs();
        if(loc==null && skill ==null){
            List<JobsDto> jobsDtoList = jobMapper.toJobDtoList(jobsList);
            return ResponseEntity.ok().body(jobsDtoList);
        } else if (loc==null) {
            List<Jobs> jobsList1 = helper.getJobsSearchBySkills(jobsList,skill);
            List<JobsDto> jobsDtoList1 = jobMapper.toJobDtoList(jobsList1);
            return ResponseEntity.ok().body(jobsDtoList1);
        } else if (skill==null) {
            List<Jobs> jobsList2=jobService.getJobsSearchByLocation(loc);
            List<JobsDto> jobsDtoList2 = jobMapper.toJobDtoList(jobsList2);
            return ResponseEntity.ok().body(jobsDtoList2);
        } else{
            List<Jobs> jobsByLoc =jobService.getJobsSearchByLocation(loc);
            List<Jobs> jobsByLocSkill=helper.getJobsSearchBySkills(jobsByLoc,skill);
            List<JobsDto> jobsDtoList3 = jobMapper.toJobDtoList(jobsByLocSkill);
            return ResponseEntity.ok().body(jobsDtoList3);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobsDto> getJobById(@PathVariable(value="id") int id){
        Optional<Jobs> job = jobService.getJobById(id);
        if (job.isEmpty()){
            throw new DataNotFoundException("No job found with id: "+id);
        }
        JobsDto jobDto = jobMapper.toJobsDto(job.get());
        return ResponseEntity.ok().body(jobDto);
    }
}