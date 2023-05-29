package com.simplifysynergy.cqrs.query.domain.mapper;


import com.simplifysynergy.cqrs.common.UserDto;
import com.simplifysynergy.cqrs.query.domain.entity.User;

public class UserMapper {
    private UserMapper() {
    }

    public static User mapToUser(User original, User copy) {
        original.setGender(copy.getGender());
        original.setAge(copy.getAge());
        original.setLastName(copy.getLastName());
        original.setFirstName(copy.getFirstName());
        original.setDateOfBirth(copy.getDateOfBirth());
        return original;
    }

    public static User mapUserEntityToUserDto(UserDto user) {
        return User.builder()
                .id(user.getId())
                .gender(user.getGender())
                .age(user.getAge())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

}

