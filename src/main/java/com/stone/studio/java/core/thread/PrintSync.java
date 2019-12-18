package com.stone.studio.java.core.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程交替打印： ABCABCABC
 */
public class PrintSync {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        new Thread(new PrintTask(lock, c1, c2, "A", 3)).start();
        new Thread(new PrintTask(lock, c2, c3, "B", 3)).start();
        new Thread(new PrintTask(lock, c3, c1, "C", 3)).start();

        // 等待上面的子线程准备好
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 主线程唤醒第一个子线程开始执行
        try{
            lock.lock();
            c1.signal();
        } finally {
            lock.unlock();
        }
    }
}

class PrintTask implements Runnable {
    private ReentrantLock lock; // condition需要的锁
    private Condition cur; // 对应当前线程的条件
    private Condition next; // 对应下一个打印线程的条件
    private String id; // 打印字符串
    private int count; // 打印次数

    public PrintTask(ReentrantLock lock, Condition cur, Condition next, String id, int count) {
        this.lock = lock;
        this.cur = cur;
        this.next = next;
        this.id = id;
        this.count = count;
    }

    /**
     * 打印当前线程字符串，并唤醒下一个线程进行打印。
     * 第一个线程首次唤醒需要等待主线程唤醒
     */
    public void run() {
        for(int i=0; i<count; i++) {
            try{
                lock.lock();
                cur.await();
                System.out.println(id);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}