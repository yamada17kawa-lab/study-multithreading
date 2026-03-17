package com.nuliyang.multithreading.some_function;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "sleep")
public class Sleep {

    private static void sleep_state() throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        t1.start();
        System.out.println(t1.getState());

        Thread.sleep(300);

        System.out.println(t1.getState());
    }


    private static void sleep_interrupt() throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                try {
                    log.info("enter sleep");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.info("wake up……");
                    throw new RuntimeException(e);
                }

            }
        };

        t1.start();

        Thread.sleep(300);
        //打断睡眠
        log.info("interrupt……");
        t1.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
//        sleep_state();
        sleep_interrupt();

    }
}
