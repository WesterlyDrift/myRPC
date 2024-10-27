package com.howard.rpc.myRPCv3.server;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class SimpleRPCServer implements RPCServer {
    private ServiceProvider serviceProvider;

    public SimpleRPCServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server starts at port: " + port);

            while(true) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serviceProvider)).start();
            }
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Server start failed");
        }
    }
}
