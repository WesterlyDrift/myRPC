package com.howard.rpc.myRPCv2.service;

import com.howard.rpc.myRPCv2.common.User;

public interface UserService {
    User getUserById(Integer id);

    Integer insertUserId(User user);
}
