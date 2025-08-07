package com.xiaoyogncai.io.handler;


import com.xiaoyogncai.io.XExecutorService;

public class ThrowRejectHandle implements RejectHandle{
    @Override
    public void reject(Runnable rejectedCommand, XExecutorService threadPool) {
        throw new RuntimeException("阻塞队列满了");
    }
}
