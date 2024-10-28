package com.howard.rpc.common.service;

import com.howard.rpc.common.pojo.User;
public interface UserService {
    User getUserById(Integer id);
    Integer insertUserId(User user);
}
