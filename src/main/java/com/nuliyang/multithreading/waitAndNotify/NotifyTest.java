package com.nuliyang.multithreading.waitAndNotify;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "NotifyTest")
public class NotifyTest {

    private static final Object obj = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("t1 start");
            synchronized (obj) {
                log.info("t1 is going to wait");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("t1 is waked up");
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.info("t2 start");
            synchronized (obj) {
                log.info("t2 is going to wait");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("t2 is waked up");
            }
        }, "t2");


        t1.start();
        t2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("main is going to notify");
        synchronized ( obj){

            obj.notify();
        }
    }
}
