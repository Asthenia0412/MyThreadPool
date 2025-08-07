package com.xiaoyogncai.io;

public interface XExecutorService {

    void execute(Runnable runnable);

    Object submit(Runnable runnable);

    Object shutdownGracefully();
}
