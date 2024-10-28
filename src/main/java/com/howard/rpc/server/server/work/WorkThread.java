package com.howard.rpc.server.server.work;


import com.howard.rpc.common.message.RpcRequest;
import com.howard.rpc.common.message.RpcResponse;
import com.howard.rpc.server.provider.ServiceProvider;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

@AllArgsConstructor
public class WorkThread implements Runnable {
    private Socket socket;
    private ServiceProvider serviceProvider;

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            RpcRequest request = (RpcRequest) in.readObject();
            RpcResponse response = getResponse(request);

        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("输出流创建失败");
        }
    }

    private RpcResponse getResponse(RpcRequest request) {
        String interfaceName = request.getInterfaceName();
        Object service = serviceProvider.getService(interfaceName);
        Method method = null;

        try {
            method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object invokeResult = method.invoke(service, request.getParams());
            return RpcResponse.success(invokeResult);

        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("Method invoke failed");
            return RpcResponse.fail();
        }
    }
}
