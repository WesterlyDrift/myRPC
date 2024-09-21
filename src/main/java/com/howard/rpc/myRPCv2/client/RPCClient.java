package com.howard.rpc.myRPCv2.client;

import com.howard.rpc.myRPCv2.client.ClientProxy;
import com.howard.rpc.myRPCv2.common.Blog;
import com.howard.rpc.myRPCv2.common.LoggerSingleton;
import com.howard.rpc.myRPCv2.common.User;
import com.howard.rpc.myRPCv2.service.BlogService;
import com.howard.rpc.myRPCv2.service.UserService;
import org.slf4j.Logger;



public class RPCClient {
    private static final Logger log = LoggerSingleton.getLogger();
    public static void main(String[] args) {

        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 65535);
        UserService userServiceProxy = clientProxy.getProxy(UserService.class);

        // version 0 service method 1
        User userByUserId = userServiceProxy.getUserById(10);
        log.info("Get user: {} from server.", userByUserId);
        System.out.printf("Get user: {%s} from server\n", userByUserId);

        // version 1 service method 2
        User user = User.builder()
                .id(100)
                .userName("Michael")
                .sex(true)
                .build();
        Integer userId = userServiceProxy.insertUserId(user);
        log.info("Insert user: {} into server, userId: {}.", user, userId);
        System.out.printf("Insert user: {%s} into server, userId: {%d}.\n", user, userId);

        // version 2 service method 3
        BlogService blogService = clientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(1000);
        System.out.println("Get blog " + blogById + " from server");
    }
}
