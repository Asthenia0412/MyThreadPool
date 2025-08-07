package com.xiaoyongcai.io.handler;

import com.xiaoyongcai.io.XExecutorService;

public class ThrowRejectHandle implements RejectHandle{
    @Override
    public void reject(Runnable rejectedCommand, XExecutorService threadPool) {
        throw new RuntimeException("阻塞队列满了");
    }
}
