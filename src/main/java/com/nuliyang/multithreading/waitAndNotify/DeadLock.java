package com.nuliyang.multithreading.waitAndNotify;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "DeadLock")
public class DeadLock {

    static final Object A = new Object();

    static final Object B = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (A){
                log.info("t1 get A");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("t1 want to get B");
                synchronized (B){
                    log.info("t1 get B");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (B){
                log.info("t2 get B");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("t2 want to get A");
                synchronized (A){
                    log.info("t2 get A");
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
