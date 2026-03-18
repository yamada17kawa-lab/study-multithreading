package com.nuliyang.multithreading.reentrantlock;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@Slf4j(topic = "ConditionTest")
public class ConditionTest {


    static final ReentrantLock reentrantLock = new ReentrantLock();


    /**
     * 可以讲condition理解为一个房间
     */
    static final Condition cigar = reentrantLock.newCondition();

    static final Condition cake = reentrantLock.newCondition();

    static boolean hasCake = false;

    static boolean hasCigar = false;

    public static void main(String[] args) {
        Thread xiaonan = new Thread(() -> {
            try {
                while (!hasCake){
                    if (reentrantLock.tryLock()){
                        try {
                            log.info("没蛋糕，再等会");
                            cake.await();
                            log.info("吃蛋糕咯！");
                            sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }finally {
                log.info("小南锁释放了");
                reentrantLock.unlock();
            }

        }, "小南");


        Thread xiaoxi = new Thread(() -> {
            try {
                while (!hasCigar){
                    if (reentrantLock.tryLock()){
                        try {
                            log.info("草泥马，我烟呢！");
                            cigar.await();
                            log.info("我吸我吸！");
                            sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } finally {
                log.info("小西锁释放了");
                reentrantLock.unlock();
            }

        }, "小西");


        Thread songCake = new Thread(() -> {
            try {
                while (!hasCake) {
                    if (reentrantLock.tryLock()){
                        log.info("哈哈哈，蛋糕来咯");
                        hasCake = true;
                        sleep(1000);
                        cake.signal();
                    }
                }
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            finally {
                log.info("送蛋糕锁释放了");
                reentrantLock.unlock();
            }

        }, "送蛋糕");

        Thread songSigar = new Thread(() -> {
            try {
                while (!hasCigar) {
                    if (reentrantLock.tryLock()){
                        log.info("哈哈哈，烟来咯");
                        hasCigar = true;
                        sleep(1000);
                        cigar.signal();
                    }
                }
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            finally {
                log.info("送烟锁释放了");
                reentrantLock.unlock();
            }

        }, "送烟");



        xiaonan.start();
        xiaoxi.start();
        songCake.start();
        songSigar.start();



    }
}
