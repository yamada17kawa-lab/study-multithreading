package com.nuliyang.multithreading.reentrantlock;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "Philosopher")
public class PhilosopherTest {



    public static void main(String[] args) {
        Chopsticks c1 = new Chopsticks("1");
        Chopsticks c2 = new Chopsticks("2");
        Chopsticks c3 = new Chopsticks("3");
        Chopsticks c4 = new Chopsticks("4");
        Chopsticks c5 = new Chopsticks("5");

        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("阿基米德", c4, c5).start();
        new Philosopher("阿勒颇", c5, c1).start();
    }



}

@Slf4j
class Philosopher extends Thread {
    Chopsticks left;
    Chopsticks right;
    String name;

    public Philosopher(String name, Chopsticks left, Chopsticks right) {
        super(name);
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while ( true){
            if (left.tryLock()){
                //获取到左筷子
                try {
                    if (right.tryLock()){
                        //获取到右筷子
                        try {
                            eat();
                            break;
                        } finally {
                            //关键是这里，如果获取不到就释放锁，避免死锁
                            right.unlock();
                        }
                    }
                }finally {
                    //关键是这里，如果获取不到就释放锁，避免死锁
                    left.unlock();
                }
            }
        }
    }

    private void eat() {
        log.info("{} is eating", name);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


class Chopsticks extends ReentrantLock{
    String name;

    public Chopsticks(String name) {
        this.name = name;
    }


}
