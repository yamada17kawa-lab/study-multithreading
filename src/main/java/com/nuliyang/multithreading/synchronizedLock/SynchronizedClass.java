package com.nuliyang.multithreading.synchronizedLock;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "SynchronizedClass")
public class SynchronizedClass {

    public static void main(String[] args) {
        Room room = new Room();

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 500000; i++) {
                room.add();
            }
        },"add");

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 500000; i++) {
                room.sub();
            }
        },"sub");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("count="+room.getCount());
    }



}

class Room {
    private int count = 0;

    public void add() {
        synchronized (this) {
            count++;
        }
    }

    public void sub() {
        synchronized (this) {
            count--;
        }
    }

    public int getCount() {
        synchronized (this) {
            return count;
        }
    }
}
