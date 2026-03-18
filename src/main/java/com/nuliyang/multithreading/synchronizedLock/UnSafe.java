package com.nuliyang.multithreading.synchronizedLock;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "UnSafe")
public class UnSafe {

    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {


        int times = 500000;


        Thread t1 = new Thread(()->{
            for (int i = 0; i < times; i++) {
                count++;
            }
        },"add");

        Thread t2 = new Thread(()->{
            for (int i = 0; i < times; i++) {
                count--;
            }
        },"sub");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.info("count={}", count);
    }
}
