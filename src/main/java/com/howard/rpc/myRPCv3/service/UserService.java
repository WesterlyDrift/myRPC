package com.howard.rpc.myRPCv3.service;

import com.howard.rpc.myRPCv3.common.User;
public interface UserService {
    User getUserById(Integer id);

    Integer insertUser(User user);
}
