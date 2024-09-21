package com.howard.rpc.myRPCv1.service;

import com.howard.rpc.myRPCv1.common.User;

public interface UserService {
    User getUserById(Integer id);

    Integer insertUserId(User user);
}
