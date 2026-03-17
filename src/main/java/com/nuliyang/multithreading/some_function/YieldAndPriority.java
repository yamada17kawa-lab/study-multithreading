package com.nuliyang.multithreading.some_function;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "YieldAndPriority")
public class YieldAndPriority {


    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                int count = 0;
                for(; ;){
                    System.out.println("-->1 "+ count++);
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run(){
                int count = 0;
                for(; ;){
//                    Thread.yield();
                    System.out.println("     --->2 "+ count++);
                }
            }
        };


        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }
}
