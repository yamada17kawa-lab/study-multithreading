package com.nuliyang.multithreading.volatile_test;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "VolatileTest")
public class VolatileTest {


    /**
     * 保证了可见性
     * 可见性即是直接操作主内存
     * 但不保证原子性
     */
    static volatile boolean running = true;

    public static void main(String[] args) {
        new  Thread(() -> {
            while (true){
                if (!running){
                    break;
                }
            }
        }, "t1").start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        running = false;
        log.info("修改running为false");
    }
}
