package com.xiaoyongcai.io;

import com.xiaoyongcai.io.handler.ThrowRejectHandle;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

public class TestMain {
    public static void main(String[] args){
        ThrowRejectHandle handle = new ThrowRejectHandle();
        XExecutorService threadPool = new XThreadPool(2,4,1, TimeUnit.SECONDS,
                handle,new ArrayBlockingQueue(1));

        for(int i=0;i<5;i++){
            threadPool.execute(()->{
                System.out.println("测试消息"+Thread.currentThread().getName());
            });
        }
    }
}
