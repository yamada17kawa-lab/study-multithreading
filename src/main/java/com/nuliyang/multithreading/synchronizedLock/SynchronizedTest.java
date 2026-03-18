package com.nuliyang.multithreading.synchronizedLock;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "SynchronizedTest")
public class SynchronizedTest {


    private static int count = 0;

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {


        int times = 500000;


        Thread t1 = new Thread(()->{
            for (int i = 0; i < times; i++) {
                synchronized (lock){
                    count++;
                }

            }
        },"add");

        Thread t2 = new Thread(()->{
            for (int i = 0; i < times; i++) {
                synchronized (lock){
                    count--;
                }
            }
        },"sub");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.info("count={}", count);
    }
}
