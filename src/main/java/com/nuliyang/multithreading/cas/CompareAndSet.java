package com.nuliyang.multithreading.cas;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j(topic = "CompareAndSet")
public class CompareAndSet {

    public static void main(String[] args) {
        Account account = new Account1(10000);
        Account.demo(account);

    }


}

class Account1 implements Account {
    private AtomicInteger balance;

    public Account1(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }
    @Override
    public void withdraw(Integer amount) {
        while ( true){
            int pre = this.balance.get();

            int target = pre - amount;

            /**
             * compareAndSet方法返回true表示修改成功，返回false表示修改失败
             * compareAndSet方法会先获取当前值，然后判断当前值是否等于pre
             * 如果等于pre则将target值设置给balance，并返回true，否则返回false
             */
            if (this.balance.compareAndSet(pre, target)){
                break;
            }
        }

    }

    @Override
    public Integer getBalance() {
        return this.balance.get();
    }
}


interface Account {
    void withdraw(Integer amount);

    Integer getBalance();

    static void demo(Account account) {
        ArrayList<Thread> list = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            list.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        list.forEach(Thread::start);
        long start = System.nanoTime();
        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance() + " cost: " + (end - start) + " ns");

    }

}