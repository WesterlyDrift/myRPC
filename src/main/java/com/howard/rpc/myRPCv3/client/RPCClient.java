package com.howard.rpc.myRPCv3.client;

import com.howard.rpc.myRPCv3.common.RPCRequest;
import com.howard.rpc.myRPCv3.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
