package com.howard.rpc.myRPCv3.client;

import com.howard.rpc.myRPCv3.common.Blog;
import com.howard.rpc.myRPCv3.common.User;
import com.howard.rpc.myRPCv3.common.RPCRequest;
import com.howard.rpc.myRPCv3.common.RPCResponse;
import com.howard.rpc.myRPCv3.service.BlogService;
import com.howard.rpc.myRPCv3.service.UserService;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@AllArgsConstructor
public class SimpleRPCClient implements RPCClient {
    // BIO
    private String host;
    private int port;

    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        try {
            Socket socket = new Socket(host, port);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Client sends request: " + request);
            out.writeObject(request);
            out.flush();

            RPCResponse response = (RPCResponse) in.readObject();
            System.out.println(response.getData());
            return response;

        } catch(IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
