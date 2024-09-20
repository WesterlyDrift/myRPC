package com.howard.rpc.myRPCv0.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerSingleton {
    private static Logger logger;
    private LoggerSingleton() {}

    public static Logger getLogger() {
        if(logger == null) {
            synchronized (LoggerSingleton.class) {
                if(logger == null) {
                    logger = LoggerFactory.getLogger(LoggerSingleton.class);
                }
            }
        }
        return logger;
    }
}
