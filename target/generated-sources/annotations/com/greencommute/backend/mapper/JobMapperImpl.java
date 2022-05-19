package com.greencommute.backend.mapper;

import com.greencommute.backend.dto.JobsDto;
import com.greencommute.backend.entity.CommuteRoutes;
import com.greencommute.backend.entity.Jobs;
import com.greencommute.backend.entity.Skills;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-19T16:43:22+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public JobsDto toJobsDto(Jobs optionalJobs) {
        if ( optionalJobs == null ) {
            return null;
        }

        JobsDto jobsDto = new JobsDto();

        jobsDto.setJobId( optionalJobs.getJobId() );
        jobsDto.setJobTitle( optionalJobs.getJobTitle() );
        jobsDto.setJobDescription( optionalJobs.getJobDescription() );
        jobsDto.setJobLocation( optionalJobs.getJobLocation() );
        List<Skills> list = optionalJobs.getSkillList();
        if ( list != null ) {
            jobsDto.setSkillList( new ArrayList<Skills>( list ) );
        }
        List<CommuteRoutes> list1 = optionalJobs.getCommuteList();
        if ( list1 != null ) {
            jobsDto.setCommuteList( new ArrayList<CommuteRoutes>( list1 ) );
        }

        return jobsDto;
    }

    @Override
    public List<JobsDto> toJobDtoList(List<Jobs> jobsList) {
        if ( jobsList == null ) {
            return null;
        }

        List<JobsDto> list = new ArrayList<JobsDto>( jobsList.size() );
        for ( Jobs jobs : jobsList ) {
            list.add( toJobsDto( jobs ) );
        }

        return list;
    }
}
