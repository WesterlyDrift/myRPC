package com.howard.rpc.myRPCv2.server;

import com.howard.rpc.myRPCv2.service.BlogService;
import com.howard.rpc.myRPCv2.service.UserService;

import java.util.HashMap;
import java.util.Map;
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RPCServer rpcServer = new ThreadPoolRPCServer(serviceProvider);
        rpcServer.start(65535);
    }
}
