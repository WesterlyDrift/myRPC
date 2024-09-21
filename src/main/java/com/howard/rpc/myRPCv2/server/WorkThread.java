package com.howard.rpc.myRPCv2.server;

import com.howard.rpc.myRPCv2.common.RPCRequest;
import com.howard.rpc.myRPCv2.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

@AllArgsConstructor
public class WorkThread implements Runnable {

    private Socket socket;
    private ServiceProvider serviceProvider;

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            RPCRequest request = (RPCRequest) in.readObject();
            RPCResponse response = getResponse(request);

            out.writeObject(response);
            out.flush();

        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error while reading data from IO stream");
        }
    }

    private RPCResponse getResponse(RPCRequest request) {
        String interfaceName = request.getInterfaceName();
        Object service = serviceProvider.getService(interfaceName);
        Method method = null;

        try {
            method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object invokeResult = method.invoke(service, request.getParams());
            return RPCResponse.success(invokeResult);

        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("Error while invoke method");
            return RPCResponse.fail();
        }
    }
}
