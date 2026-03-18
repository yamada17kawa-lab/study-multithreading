ReentrantLock简介
    1、ReentrantLock是java.util.concurrent包下的一个类，它是Lock接口的实现类。
    2、ReentrantLock类提供了比synchronized更多样的方法和语句
    3、相比synchronized，额外有：
        1）tryLock()方法，尝试获取锁
        2）tryLock(long timeout, TimeUnit unit)方法，尝试获取锁，并指定超时时间
        3）lockInterruptibly()方法，获取锁，如果获取不到，则一直等待，直到被中断
        4）需要手动加锁解锁

lockInterruptibly()方法
    1、lockInterruptibly()方法，获取锁，如果获取不到，则一直等待，可以被打断


tryLock()方法
    1、tryLock()方法，尝试获取锁，如果获取不到，则返回false，继续往下走
    2、tryLock(long timeout, TimeUnit unit)方法，在指定时间内尝试获取锁
    3、可以避免死锁，等不到就算了

公平锁
    先到先得

条件变量
    可以将condition理解为一个房间
    1、await前需要获得锁
    2、await执行后，会释放锁，进入conditionObject等待
    3、await的线程被唤醒(或打断、或超时)后重新竞争lock锁
    4、竞争lock锁成功后，从await后继续执行