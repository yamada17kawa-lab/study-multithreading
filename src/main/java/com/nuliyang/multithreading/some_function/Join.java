package com.nuliyang.multithreading.some_function;


import lombok.extern.slf4j.Slf4j;


@Slf4j(topic = "Join")
public class Join {
    static int r = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            log.info("开始");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            r = 10;
            log.info("结束");
        }, "t1");

        t1.start();
        //堵塞当前线程，等待t1线程结束
        t1.join();
        log.info("r={}", r);



    };
}
