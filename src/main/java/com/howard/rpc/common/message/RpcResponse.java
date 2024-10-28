package com.howard.rpc.common.message;

import lombok.Data;
import lombok.Builder;

import java.io.Serializable;

@Data
@Builder
public class RpcResponse implements Serializable {
    private int code;
    private String message;
    private Object data;

    public static RpcResponse success(Object data) {
        return RpcResponse.builder().code(200).data(data).build();
    }

    public static RpcResponse fail() {
        return RpcResponse.builder().code(500).message("error").build();
    }
}
