package com.simplifysynergy.cqrs.command.domain.mapper;

import com.simplifysynergy.cqrs.command.domain.entity.User;
import com.simplifysynergy.cqrs.common.UserDto;

public class UserMapper {
    private UserMapper() {
    }

    public static void mapToUser(User original, User copy) {
        original.setGender(copy.getGender());
        original.setAge(copy.getAge());
        original.setLastName(copy.getLastName());
        original.setFirstName(copy.getFirstName());
        original.setDateOfBirth(copy.getDateOfBirth());
    }

    public static UserDto mapUserEntityToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .gender(user.getGender())
                .age(user.getAge())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

}

