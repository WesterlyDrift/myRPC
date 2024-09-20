package com.howard.rpc.myRPCv0.server;

import com.howard.rpc.myRPCv0.common.User;
import com.howard.rpc.myRPCv0.common.LoggerSingleton;

import org.slf4j.Logger;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    private static Logger log = LoggerSingleton.getLogger();

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        try {
            ServerSocket serverSocket = new ServerSocket(65535);
            log.info("Server start success!");
            System.out.println("Server start success!");

            while(true) {
                Socket client = serverSocket.accept();
                new Thread(() -> {
                    try {

                        ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                        Integer id = in.readInt();
                        User user = userService.getUserById(id);

                        out.writeObject(user);
                        out.flush();

                    } catch (IOException e) {
                        log.error("Server failed to get User: \n" + e.getMessage());
                        System.out.println("Server failed to get User: \n" + e.getMessage());
                    }
                }).start();
            }

        } catch (IOException e) {
            log.error("Server start failed: \n" + e.getMessage());
            System.out.println("Server start failed: \n" + e.getMessage());
        }
    }
}
