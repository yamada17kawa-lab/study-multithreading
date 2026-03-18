wait
    1、线程从Runnable状态转换成Waiting状态
    2、wait方法调用后，线程进入waitset集合
    3、无参数的wait方法会无限期的等下去，有参数的超时就会自动到entryliset集合

notify
    1、随机唤醒一个在waitset中的线程到entrylist集合

notifyAll
    1、唤醒所有在waitset中的线程到entrylist集合

为什么需要waitset集合
    因为如果线程wait后进入的是entrylist又抢到锁，但是条件依旧没有满足，还是无法跑下去，就浪费资源，所以需要一个waitset集合

wait和sleep的区别
    1、sleep是Thread类方法，wait是Object类方法
    2、sleep不会释放锁，wait会释放锁

优雅使用wait
    1、使用while循环，避免虚假唤醒，如果发现唤醒后条件依旧不满足就继续wait

死锁
    多线程各自持有一把锁，然后它们互相等对方释放锁

检测死锁
    jsp查java进程 --> jstack java进程id --> 死锁
    jconsole图像化界面 --> 死锁