package com.nuliyang.multithreading.waitAndNotify;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "WaitWithArg")
public class WaitWithArg {

    private static final Object obj = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized ( obj){
                log.info("t1 start");
                try {
                    obj.wait(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("t1 is waked up");
            }
        }, "t1");


        t1.start();

        log.info("main is going");

    }
}
