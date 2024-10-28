package com.howard.rpc.server.server.impl;

import lombok.AllArgsConstructor;
import com.howard.rpc.server.provider.ServiceProvider;
import com.howard.rpc.server.server.RpcServer;
import com.howard.rpc.server.server.work.WorkThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@AllArgsConstructor
public class SimpleRPCServer implements RpcServer  {
    private ServiceProvider serviceProvider;

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server start success");

            while(true) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serviceProvider)).start();
            }

        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("server start error");
        }
    }

    @Override
    public void stop() {

    }
}
