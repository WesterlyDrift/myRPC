package com.howard.rpc.myRPCv2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleRPCServer implements RPCServer {
    private ServiceProvider serviceProvider;

    public SimpleRPCServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Simple RPC Server started on port: " + port);

            while(true) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serviceProvider)).start();
            }

        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Simple RPC Server start error");
        }
    }

    @Override
    public void stop() {}
}
