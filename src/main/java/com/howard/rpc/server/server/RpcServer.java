package com.howard.rpc.server.server;

public interface RpcServer {
    void start(int port);
    void stop();
}
