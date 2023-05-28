package com.simplifysynergy.cqrs.common.util;

import com.simplifysynergy.cqrs.common.domain.User;

public class UserMapper {

    private UserMapper() {}

    public static User mapToUser(User original, User copy) {
        original.setAge(copy.getAge());
        original.setGender(copy.getGender());
        original.setLastName(copy.getLastName());
        original.setFirstName(copy.getFirstName());
        original.setDateOfBirth(copy.getDateOfBirth());
        return original;
    }
}