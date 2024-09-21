package com.howard.rpc.myRPCv1.server;

import com.howard.rpc.myRPCv1.common.LoggerSingleton;
import com.howard.rpc.myRPCv1.common.User;
import com.howard.rpc.myRPCv1.service.UserService;
import org.slf4j.Logger;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    Logger log = LoggerSingleton.getLogger();

    @Override
    public User getUserById(Integer id) {
        log.info("Client queries user of id: " + id);
        System.out.println("Client queries user of id: " + id);

        Random random = new Random();
        return User.builder()
                .userName(UUID.randomUUID().toString())
                .sex(random.nextBoolean())
                .id(id)
                .build();
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("Insert user success: " + user);
        return 1;
    }
}
