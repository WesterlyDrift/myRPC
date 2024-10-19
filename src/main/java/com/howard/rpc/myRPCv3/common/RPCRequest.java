package com.howard.rpc.myRPCv3.common;

import lombok.Data;
import lombok.Builder;

import java.io.Serializable;

@Data
@Builder
public class RPCRequest implements Serializable {
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramsTypes;
}
