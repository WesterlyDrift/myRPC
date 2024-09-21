package com.howard.rpc.myRPCv2.common;

import lombok.Data;
import lombok.Builder;

import java.io.Serializable;

@Data
@Builder
public class RPCResponse implements Serializable {
    private int code;
    private String message;
    private Object data;

    public static RPCResponse success(Object data) {
        return RPCResponse.builder()
                .code(200)
                .data(data)
                .message("Success")
                .build();
    }

    public static RPCResponse fail() {
        return RPCResponse.builder()
                .code(500)
                .message("Server error")
                .build();
    }
}
