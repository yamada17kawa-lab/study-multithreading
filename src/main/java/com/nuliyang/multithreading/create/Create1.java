package com.nuliyang.multithreading.create;


import lombok.extern.slf4j.Slf4j;

/**
 * 直接创建线程
 */
@Slf4j(topic = "Create1")
public class Create1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                log.info("running1");
            }
        };

        t1.setName("t1");

        t1.start();


        log.info("running2");
    }
}
