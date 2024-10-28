package com.howard.rpc.common.service.impl;

import com.howard.rpc.common.pojo.User;
import com.howard.rpc.common.service.UserService;
import com.howard.rpc.common.pojo.GenderEnum;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        Random random = new Random();
        User user = User.builder()
                .id(id)
                .userName(UUID.randomUUID().toString())
                .gender(random.nextBoolean() ? GenderEnum.MALE : GenderEnum.FEMALE)
                .build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        return user.getId();
    }
}
