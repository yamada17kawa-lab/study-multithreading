package com.nuliyang.multithreading.some_function;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Practice")
public class Practice {

    public static void main(String[] args) {
        Runnable shaokaishui = new Runnable() {
            @Override
            public void run() {
                log.info("烧开水");
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        };


        Runnable nachaye = new Runnable() {
            @Override
            public void run() {
                log.info("拿茶叶");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        };

        Runnable xichahu = new Runnable() {
            @Override
            public void run() {
                log.info("洗茶壶");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        Runnable cichabei = new Runnable() {
            @Override
            public void run() {
                log.info("洗茶杯");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable xishuihu = new Runnable() {
            @Override
            public void run() {
                log.info("洗水壶");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t1 = new Thread(shaokaishui);
    }
}
