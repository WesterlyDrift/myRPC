package com.howard.rpc.myRPCv0.client;

import com.howard.rpc.myRPCv0.common.User;
import com.howard.rpc.myRPCv0.common.LoggerSingleton;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClient {
    private static final Logger logger = LoggerSingleton.getLogger();
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 65535);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeInt(new Random().nextInt(10000));
            out.flush();

            User user = (User) in.readObject();
            logger.info("Get user from server: " + user);
            System.out.println("Get user from server: " + user);

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            System.out.println("Client initialization failed!");
        }
    }
}
