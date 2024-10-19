package com.howard.rpc.myRPCv3.service;

import com.howard.rpc.myRPCv3.common.User;
import com.howard.rpc.myRPCv3.service.UserService;
import java.util.UUID;
import java.util.Random;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        System.out.println("Client queried user of id: " + id);
        return User.builder()
                .id(id)
                .userName(String.valueOf(UUID.randomUUID()))
                .sex(new Random().nextBoolean())
                .build();
    }

    @Override
    public Integer insertUser(User user) {
        System.out.println("Client insert user: " + user);
        return 1;
    }
}
