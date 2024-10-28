package com.howard.rpc.client;

import com.howard.rpc.common.pojo.GenderEnum;
import com.howard.rpc.common.pojo.User;
import lombok.extern.slf4j.Slf4j;
import com.howard.rpc.common.service.UserService;
import com.howard.rpc.client.proxy.ClientProxy;

public class TestClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy();
        UserService proxy = clientProxy.getProxy(UserService.class);

        User user = proxy.getUserById(1);
        System.out.println("Get user from server: " + user);

        User u = User.builder()
                .id(100)
                .userName("howard")
                .gender(GenderEnum.MALE)
                .build();
        Integer id = proxy.insertUserId(u);
        System.out.println("Insert user id: " + id);
    }
}
