package com.nuliyang.multithreading.some_function;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "interrupt")
public class InterruptTest {

    private static void doudi() throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    log.info("善后工作，比如处理完业务、关闭连接等等");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.info("处理事务");
                } catch (InterruptedException e) {
                    //捕获到打断中断异常，重新设置打断标记
                    log.error("捕获到打断中断异常");
                    Thread.currentThread().interrupt();
                }
            }
        },"t1");


        //启动线程
        t1.start();

        try {
            log.info("main线程正在运行");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //打断线程
        log.info("打断线程");
        t1.interrupt();

        t1.join();
    }


    private static void park(){
        Thread t2 = new Thread(()->{
            log.info("park");
            log.info("park前的中断标记为{}", Thread.currentThread().isInterrupted());
            LockSupport.park();
            log.info("unpark");
            log.info("unpark后的中断标记为{}", Thread.currentThread().isInterrupted());
            log.info("再次尝试park");
            LockSupport.park();
            log.info("由于中断标记为true，所以无法park除非手动将中断标记设为false");
            log.info("将中断标记设为false");
            Thread.interrupted();
            LockSupport.park();
        },"t2");


        //启动线程
        t2.start();

        //打断线程
        log.info("打断线程");
        t2.interrupt();

    }

    public static void main(String[] args) throws InterruptedException {
        park();
    }
}
