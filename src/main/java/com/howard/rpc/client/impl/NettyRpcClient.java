package com.howard.rpc.client.impl;

import com.howard.rpc.client.netty.nettyInitializer.NettyClientInitializer;
import com.howard.rpc.client.serviceCenter.ServiceCenter;
import com.howard.rpc.client.serviceCenter.ZKServiceCenter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import com.howard.rpc.common.message.RpcRequest;
import com.howard.rpc.common.message.RpcResponse;
import com.howard.rpc.client.RpcClient;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

public class NettyRpcClient implements RpcClient {
    private static final Bootstrap bootstrap;;
    private static final EventLoopGroup eventLoopGroup;

    private ServiceCenter serviceCenter;

    public NettyRpcClient() {
        this.serviceCenter = new ZKServiceCenter();
    }

    static {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
    }

    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        InetSocketAddress address = serviceCenter.serviceDiscovery(request.getInterfaceName());
        String host = address.getHostName();
        int port = address.getPort();

        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();

            channel.writeAndFlush(request);
            channel.closeFuture().sync();
            AttributeKey<RpcResponse> key = AttributeKey.valueOf("RPCResponse");
            RpcResponse response = channel.attr(key).get();

            System.out.println(response);
            return response;

        } catch(InterruptedException e) {
            e.printStackTrace();
            System.out.println("sendRequest error");
        }
        return null;
    }
}
