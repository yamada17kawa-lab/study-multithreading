创建线程
    1、直接创建线程
    2、创建Runnable接口实现类
    3、创建FutureTask配合Thread，可堵塞获取值

线程执行顺序
    1、交替执行
    2、顺序不受人为控制

linux查看线程的方法
    1、ps -ef   查看所有进程
    2、ps -tf -p <pid>  查看某个进程的所有线程
    3、kill <pid>    杀死进程

