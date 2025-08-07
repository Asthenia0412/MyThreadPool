package com.xiaoyogncai.io;


import com.xiaoyogncai.io.handler.RejectHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class XThreadPool implements XExecutorService{

    // ——————————————用户自定义参数
    private int corePoolSize;

    private int maxPoolSize;

    private int timeout;

    private TimeUnit timeUnit;

    private RejectHandle rejectHandle;

    private BlockingQueue<Runnable> blockingQueue;

    // ——————————————内部结构参数

    List<Thread> coreList = new ArrayList<>();
    List<Thread> supportList = new ArrayList<>();

    class CoreThread extends Thread{
        @Override
        public void run(){
            while(true){
                try{
                    Runnable command =blockingQueue.take();
                    command.run();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    class SupportThread extends Thread{
        @Override
        public void run(){
            while(true){
                try{
                    Runnable command = blockingQueue.poll(timeout,timeUnit);
                    if ( command == null){
                        break;
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();;
                }
                System.out.println("辅助线程结束了"+Thread.currentThread().getName());
            }
        }
    }



    public XThreadPool(int corePoolSize,int maxPoolSize,int timeout,TimeUnit timeUnit,RejectHandle rejectHandle,BlockingQueue<Runnable> blockingQueue){
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectHandle = rejectHandle;
        this.blockingQueue = blockingQueue;
    }


    @Override
    public void execute(Runnable runnable) {
        if(coreList.size() < corePoolSize){
            Thread thread =new CoreThread();
            coreList.add(thread);
            thread.start();
        }

        if(coreList.size() + supportList.size() <= maxPoolSize){
            Thread thread = new SupportThread();
            supportList.add(thread);
            thread.start();
        }
        if(!blockingQueue.offer(runnable)){
            rejectHandle.reject(runnable
            ,this);
        }
    }

    @Override
    public Object submit(Runnable runnable) {
        return null;
    }

    @Override
    public Object shutdownGracefully() {
        return null;
    }
}
