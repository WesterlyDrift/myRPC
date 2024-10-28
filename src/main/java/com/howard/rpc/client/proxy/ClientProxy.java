package com.howard.rpc.client.proxy;

import com.howard.rpc.client.RpcClient;
import com.howard.rpc.client.impl.NettyRpcClient;
import com.howard.rpc.common.message.RpcRequest;
import com.howard.rpc.common.message.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxy implements InvocationHandler {
    private RpcClient client;
    public ClientProxy() {
        client = new NettyRpcClient();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes()).build();
        RpcResponse response = client.sendRequest(request);
        return response.getData();
    }

    public <T> T getProxy(Class<T> clazz) {
        Object obj = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T) obj;
    }
}
