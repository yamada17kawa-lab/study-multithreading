package com.nuliyang.multithreading.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "Retry")
public class Retry {

    static final ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {


        Thread t1 = new Thread(() -> {

            lock.lock();

            try {
                log.info("t1启动");
                /**
                 * 默认是可重入锁
                 * 可重入锁时遇到需要同样锁的地方可以直接进入
                 * 无需重新抢锁
                 */
                m1();
            }finally {
                lock.unlock();
            }
        }, "t1");

        t1.start();


    }

    static void m1(){
        lock.lock();
        try {
            log.info("m1启动");
            m2();
        }finally {
            lock.unlock();
        }
    }

    static void m2(){
        lock.lock();
        try {
            log.info("m2启动");
        }finally {
            lock.unlock();
        }
    }
}
