package com.nuliyang.multithreading.reentrantlock;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@Slf4j(topic = "InterruptQueue")
public class InterruptQueue {

    static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                log.info("尝试获取锁");
                lock.lockInterruptibly();
                log.info("获取锁成功");
            } catch (InterruptedException e) {
                log.error("线程被中断");
            }
        }, "t1");


        log.info("main线程获取锁");
        try {
            lock.lock();
            try {
                t1.start();
                sleep(2000);
                //中断线程
                t1.interrupt();
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }finally {
            log.info("main线程释放锁");
            lock.unlock();
        }


    }
}
