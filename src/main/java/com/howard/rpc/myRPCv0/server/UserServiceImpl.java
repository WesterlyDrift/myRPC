package com.howard.rpc.myRPCv0.server;

import com.howard.rpc.myRPCv0.common.User;
import java.util.Random;
import java.util.UUID;

import com.howard.rpc.myRPCv0.service.UserService;
import org.slf4j.Logger;
import com.howard.rpc.myRPCv0.common.LoggerSingleton;

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
}
