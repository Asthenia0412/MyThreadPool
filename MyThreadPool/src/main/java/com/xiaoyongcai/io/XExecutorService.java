package com.xiaoyongcai.io;

public interface XExecutorService {

    void execute(Runnable runnable);

    Object submit(Runnable runnable);

    Object shutdownGracefully();
}
