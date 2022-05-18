package com.greencommute.backend.controller;

import com.greencommute.backend.dto.ResponseDto;
import com.greencommute.backend.dto.UserDto;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.User;
import com.greencommute.backend.exception.DataNotFoundException;
import com.greencommute.backend.mapper.UserMapper;
import com.greencommute.backend.service.SavedJobServiceImpl;
import com.greencommute.backend.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
public class UserController {

     private final UserServiceImpl userService;

     private final SavedJobServiceImpl savedJobService;

    @Autowired
    public UserController(UserServiceImpl userService, SavedJobServiceImpl savedJobService) {
        this.userService = userService;
        this.savedJobService = savedJobService;
    }

    @Autowired
    public UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "id") int id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) {
            throw new DataNotFoundException("No user with id: " + id);
        }
        UserDto userDto = userMapper.toUserDto(user.get());
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/{id}/savedJobs")
    public List<Jobs> getSavedJobsOfUser(@PathVariable(value = "id") int id) {
        return savedJobService.getSavedJobsByUserId(id);
    }

    @PostMapping("/{id}/savedJobs")
    public ResponseEntity<ResponseDto> saveJobsForUser(@PathVariable(value = "id") int id, @RequestBody Map<String,Integer> reqPayload) {
        final String jobId = "jobId";
        List<Jobs> savedJobList= savedJobService.getSavedJobsByUserId(id).stream().filter(jobs -> {
            int savedJobId=jobs.getJobId();
            return savedJobId == reqPayload.get(jobId);
        }).collect(Collectors.toList());
        if(!savedJobList.isEmpty()){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setUserId(id);
            responseDto.setJobId(reqPayload.get(jobId));
            responseDto.setMessage("Job already added to saved jobs");
            return ResponseEntity.badRequest().body(responseDto);
        }
        else {
            savedJobService.saveToSavedJobs(id, reqPayload.get(jobId));
            ResponseDto responseDto = new ResponseDto();
            responseDto.setUserId(id);
            responseDto.setJobId(reqPayload.get(jobId));
            responseDto.setMessage("Successfully added to saved jobs");
            return ResponseEntity.ok().body(responseDto);
        }
    }

    @DeleteMapping("/{id}/savedJobs")
    public ResponseEntity<ResponseDto> deleteSavedJobsOfUser(@PathVariable(value="id") int id, @RequestBody Map<String,Integer> reqPayload) {
        final String jobId = "jobId";
        Boolean res=savedJobService.deleteSavedJobs(id,reqPayload.get(jobId));
        ResponseDto responseDto=new ResponseDto();
        responseDto.setUserId(id);
        responseDto.setJobId(reqPayload.get(jobId));
        if(Boolean.TRUE.equals(res)){
            responseDto.setMessage("Successfully deleted saved job");
            return ResponseEntity.ok().body(responseDto);
        }
        else {
            responseDto.setMessage("No saved job found with user id and job id");
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}