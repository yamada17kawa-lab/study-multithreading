package com.nuliyang.multithreading.some_function;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Daemon")
public class Daemon {

    private static void daemon() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    log.info("线程被中断");
                    break;
                }
            }
            log.info("t1线程结束");
        }, "t1");

        //将t1设置为守护线程
        t1.setDaemon(true);

        t1.start();
        Thread.sleep(1000);
        log.info("main线程结束");
    }

    private static void notDaemon() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    log.info("线程被中断");
                    break;
                }
            }
            log.info("t1线程结束");
        }, "t1");


        t1.start();
        Thread.sleep(1000);
        log.info("main线程结束");
    }

    public static void main(String[] args) throws InterruptedException {
        daemon();
    }
}
