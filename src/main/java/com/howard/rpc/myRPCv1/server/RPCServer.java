package com.howard.rpc.myRPCv1.server;

import com.howard.rpc.myRPCv1.common.LoggerSingleton;
import com.howard.rpc.myRPCv1.common.RPCRequest;
import com.howard.rpc.myRPCv1.common.RPCResponse;
import com.howard.rpc.myRPCv1.common.User;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

                        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                        ObjectInputStream in = new ObjectInputStream(client.getInputStream());

                        RPCRequest request = (RPCRequest) in.readObject();

                        Method method = userService.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
                        Object invokeResult = method.invoke(userService, request.getParams());



                        out.writeObject(RPCResponse.success(invokeResult));
                        out.flush();

                    } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
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
