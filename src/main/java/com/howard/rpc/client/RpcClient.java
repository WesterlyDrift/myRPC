package com.howard.rpc.client;

import com.howard.rpc.common.message.RpcResponse;
import com.howard.rpc.common.message.RpcRequest;

public interface RpcClient {
    RpcResponse sendRequest(RpcRequest request);
}
