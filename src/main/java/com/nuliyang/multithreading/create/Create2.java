package com.nuliyang.multithreading.create;


import lombok.extern.slf4j.Slf4j;


/**
 * 创建任务，然后交给线程执行
 */
@Slf4j(topic = "Create2")
public class Create2 {

    public static void main(String[] args) {


        Runnable task = new Runnable() {
            @Override
            public void run() {
                log.info("running1");
            }
        };

        Thread t1 = new Thread(task, "t1");

        t1.start();

        log.info("running2");
    }
}
