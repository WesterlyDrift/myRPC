package com.howard.rpc.myRPCv2.client;

import com.howard.rpc.myRPCv2.common.RPCResponse;
import com.howard.rpc.myRPCv2.common.RPCRequest;
import com.howard.rpc.myRPCv2.common.LoggerSingleton;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {
    //private static final Logger log = LoggerSingleton.getLogger();

    public static RPCResponse sendRequest(String host, int port, RPCRequest request) {
        try {
            Socket socket = new Socket(host, port);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //log.info("Sending request: " + request);
            System.out.println("Sending request: " + request);

            out.writeObject(request);
            out.flush();

            RPCResponse response = (RPCResponse) in.readObject();

            return response;

        } catch (IOException | ClassNotFoundException e) {
            //log.error(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
}
