package com.greencommute.backend.mapper;

import com.greencommute.backend.dto.UserDto;
import com.greencommute.backend.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-18T09:48:19+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Private Build)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User optionalUser) {
        if ( optionalUser == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( optionalUser.getUserId() );
        userDto.setUserName( optionalUser.getUserName() );

        return userDto;
    }
}
