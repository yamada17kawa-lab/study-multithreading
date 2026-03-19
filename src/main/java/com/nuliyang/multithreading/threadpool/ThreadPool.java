package com.nuliyang.multithreading.threadpool;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

@Slf4j(topic = "ThreadPool")
public class ThreadPool {

    //线程工厂规范线程
    static final ThreadFactory factory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("my-thread-pool-" + t.getId());
            //设置异常处理
            t.setUncaughtExceptionHandler((t1, e) -> log.error("线程{}异常", t1.getName(), e));
            //设置为守护线程，当主线程结束，守护线程也会结束
            t.setDaemon(true);
            return t;
        }
    };

    static final ThreadPoolExecutor pool = new ThreadPoolExecutor(
            5,
            10,
            1,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100),
            factory,
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) {

        //直接使用线程池执行
        pool.execute(new Runnable() {
            @Override
            public void run() {
                log.info("我被执行了");
            }
        });

        //抛出异常，由于线程工厂设置了捕获异常，会处理异常
        pool.execute(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("我被异常了");
            }
        });


        //使用Callable、Future获取返回值
        Future<String> futureCaller = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "我是返回值";
            }
        });

        //Runnable、Future没有返回值
        Future<?> futureRunnable = pool.submit(new Runnable() {
            @Override
            public void run() {
                log.info("我也想被返回");
            }
        });

        //等待一秒，确保线程池中的线程执行完毕
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            log.info("caller获取到返回值: {}", futureCaller.get());
            log.info("runnable获取到返回值: {}", futureRunnable.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
