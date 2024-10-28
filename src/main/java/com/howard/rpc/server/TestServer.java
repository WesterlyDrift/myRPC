package com.howard.rpc.server;

import com.howard.rpc.common.service.UserService;
import com.howard.rpc.common.service.impl.UserServiceImpl;
import com.howard.rpc.server.provider.ServiceProvider;
import com.howard.rpc.server.server.RpcServer;
import com.howard.rpc.server.server.impl.NettyRPCServer;
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", 65535);
        serviceProvider.provideServiceInterface(userService);

        RpcServer server = new NettyRPCServer(serviceProvider);
        server.start(65535);
    }

}
