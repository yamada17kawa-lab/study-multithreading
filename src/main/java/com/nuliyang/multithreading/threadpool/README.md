池状态                 新任务    堵塞队列任务
    1、RUNNING          Y          Y
    2、SHUTDOWN         N          Y         不会接受新任务，但会处理队列中的任务
    3、STOP             N          N         会中断正在执行的任务，抛弃队列中的任务
    4、TIDYING          -          -         任务全部执行完成，活动线程为0即将进入终结
    5、TERMINATED       -          -         终结

构造线程池参数
    corePoolSize: 线程池核心线程数
    maximumPoolSize: 线程池最大线程数
    keepAliveTime: 非核心线程闲置超时时间
    unit: 时间单位
    workQueue: 存放任务的队列
    threadFactory: 线程工厂
    handler: 拒绝策略

队列
    ArrayBlockingQueue: 数组有界队列，存放任务的队列
    LinkedBlockingQueue: 链表无界队列，存放任务的队列

线程工厂
    统一线程名字
    设置线程
    捕获线程异常

拒绝策略
    1、AbortPolicy 默认拒绝策略，抛出RejectedExecutionException
    2、CallerRunsPolicy 让调用者运行任务
    3、DiscardPolicy 放弃此次任务
    4、DiscardOldestPolicy 丢弃队列中最老的任务，本任务取代之

线程池执行流程
    线程池中刚开始没有线程，当一个任务提交给线程池后，线程池会创建一个新线程来执行任务。
    当线程数达到corePoolSize并没有线程空闲，这时再加入任务，新加的任务会被加入workQueue 队列排队，直到有空闲的线程。
    如果队列选择了有界队列，那么任务超过了队列大小时，会创建maximumPoolSize - corePoolSize数目的线程来救急。
    如果线程到达maximunPoolSize仍然有新任务这时会执行拒绝策略。

使用线程池
    execute(Runnable task) 提交一个任务给线程池
    submit(Runnable task) 提交一个任务给线程池，返回一个Future对象，但没有返回值
    submit(Callable task) 提交一个任务给线程池，返回一个Future对象，有返回值