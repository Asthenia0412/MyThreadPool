package com.xiaoyongcai.io.handler;

import com.xiaoyongcai.io.XExecutorService;

public interface RejectHandle {
    void reject(Runnable runnable, XExecutorService threadPool);
}
