死链
    1.7的hashmap结构是数组+链表
    put会在链表的头节点插入


1.8hashmap
    数组+链表+红黑树
    数组扩容：
        超过数组的0.75长度
        链表超过8且数组小于64
    链表转红黑树
        链表超过8且数组大于64    
    get
        算出hash再算出桶下标
        链表无值返回null
        链表有值遍历取出值
        forwaringNode == -1去新数组重新找
        forwadingNode == -2用红黑树的find找
    put
        桶无值直接插入
        hash冲突扩容
        正在扩容就帮忙扩容再重新put
        链表就插到链表尾
        红黑树就用红黑树的插法
        put之后检查是否需要扩容
        

1.8concurrentHashMap
    数组+链表+红黑树
    数组扩容：
        超过数组的0.75长度
        链表超过8且数组小于64
    链表转红黑树
        链表超过8且数组大于64  
    get
        算出hash再算出桶下标
        链表无值返回null
        链表有值遍历取出值
        通过volatile保证可见性
        forwaringNode == -1去新数组重新找
        forwadingNode == -2用红黑树的find找
    put
        桶无值直接插入
        hash冲突扩容
        正在扩容就帮忙扩容再重新put
        链表就插到链表尾
        红黑树就用红黑树的插法
        put的过程使用synchronized加锁
        put之后检查是否需要扩容
        