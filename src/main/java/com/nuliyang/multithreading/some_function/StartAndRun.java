package com.nuliyang.multithreading.some_function;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "StartAndRun")
public class StartAndRun {

    private static void tryDoSomething() throws InterruptedException {
        Thread.sleep(2000);
        log.info("尝试做事");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                log.info("running1");
                try {
                    tryDoSomething();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        t1.setName("t1");

        System.out.println("线程调用前状态:()" + t1.getState());
        t1.start();
        System.out.println("线程调用后状态:()" + t1.getState());
        //直接调用run方法会视为一次普通调用，不会启动新的线程
        log.info("主线程调用run方法");
        t1.run();
    }
}
