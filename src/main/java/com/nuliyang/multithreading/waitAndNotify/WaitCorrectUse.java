package com.nuliyang.multithreading.waitAndNotify;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "WaitCorrectUse")
public class WaitCorrectUse {

    static final Object lock = new Object();

    static boolean hasCake = false;

    static boolean hasCigar = false;

    public static void main(String[] args) {
        Thread xiaonan = new Thread(() -> {
            while (!hasCake){
                synchronized (lock){
                    log.info("没蛋糕，再等会");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("吃蛋糕咯！");
        }, "小南");


        Thread xiaoxi = new Thread(() -> {
            while (!hasCigar){
                synchronized (lock){
                    log.info("草泥马，我烟呢！");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("我吸我吸！");
        }, "小西");


        Thread songCake = new Thread(() -> {
            synchronized (lock){
                hasCake = true;
                log.info("哈哈哈，蛋糕来咯");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.notifyAll();
            }
        }, "songCake");

        xiaonan.start();
        xiaoxi.start();
        songCake.start();



    }
}
