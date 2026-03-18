package com.nuliyang.multithreading.waitAndNotify;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "waitTest")
public class waitTest {

    private static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        synchronized (obj){
            log.info("准备进入睡眠");
            obj.wait();
            log.info("这里不执行，直到被唤醒");
        }
    }
}
