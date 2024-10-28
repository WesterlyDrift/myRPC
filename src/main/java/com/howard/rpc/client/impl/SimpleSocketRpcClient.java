package com.howard.rpc.client.impl;
import com.howard.rpc.client.RpcClient;
import com.howard.rpc.common.message.RpcResponse;
import com.howard.rpc.common.message.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SimpleSocketRpcClient implements RpcClient {
    private String host;
    private int port;

    public SimpleSocketRpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(request);
            out.flush();

            return (RpcResponse) in.readObject();

        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
