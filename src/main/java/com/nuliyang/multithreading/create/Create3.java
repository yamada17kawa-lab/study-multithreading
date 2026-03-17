package com.nuliyang.multithreading.create;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * FutureTask配合Thread
 */
@Slf4j(topic = "Create3")
public class Create3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("running1");
                Thread.sleep(2000);
                return "这是返回值";
            }
        });

        Thread t1 = new Thread(task, "t1");

        t1.start();

        log.info("running2");

        log.info("{}", task.get());
    }
}
