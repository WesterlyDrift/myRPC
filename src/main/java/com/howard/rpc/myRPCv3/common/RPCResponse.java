package com.howard.rpc.myRPCv3.common;

import lombok.Builder;
import lombok.Data;

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
                .message("success")
                .data(data)
                .build();
    }

    public static RPCResponse fail(String message) {
        return RPCResponse.builder()
                .code(500)
                .message("server error")
                .build();
    }
}
