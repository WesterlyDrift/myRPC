package com.howard.rpc.myRPCv2.client;


import com.howard.rpc.myRPCv2.client.IOClient;
import com.howard.rpc.myRPCv2.common.RPCRequest;
import com.howard.rpc.myRPCv2.common.RPCResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@AllArgsConstructor
public class ClientProxy implements InvocationHandler {

    private String host;
    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest request = RPCRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramsTypes(method.getParameterTypes())
                .params(args)
                .build();

        RPCResponse response = IOClient.sendRequest(host, port, request);

        System.out.println("客户端发送请求: " + request);
        System.out.println("客户端收到响应: " + response);

        if (response != null) {
            return response.getData();
        }
        return new Object();
    }

    <T> T getProxy(Class<T> clazz) {
        Object obj = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T) obj;
    }
}
