package com.xiaoyogncai.io.handler;


import com.xiaoyogncai.io.XExecutorService;

public interface RejectHandle {
    void reject(Runnable runnable, XExecutorService threadPool);
}
