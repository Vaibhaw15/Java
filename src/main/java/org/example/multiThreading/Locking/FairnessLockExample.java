package org.example.multiThreading.Locking;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;


public class FairnessLockExample {

    //Fairness -> Thread get a lock in the order they requested it(FIFO)
    //jo thread pahle qait kr rha hai usko pahle lock milega. And all ko thread milega

    //UnFairness -> Any thread can grab the lock anytime, even if others are waiting.

    // Starvation -> Starvation is a situation where a thread never gets CPU time or a
    //resource because other thread keep taking it again and again.

    //Race -> A race condition ocures when multiple threads access and modify shared data at the same time, and the final
    //result depends on which thread runs first.

    private final Lock lock = new ReentrantLock(true);

    public void accessResources(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "acquire the lock.");
            Thread.sleep(50);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }finally{
            System.out.println(Thread.currentThread().getName() + " released the lock.");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FairnessLockExample example = new FairnessLockExample();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                example.accessResources();
            }
        };
        Thread thread1 = new Thread(task,"Thread 1");
        Thread thread2 = new Thread(task,"Thread 2");
        Thread thread3 = new Thread(task,"Thread 3");

        try {
            thread1.start();
            Thread.sleep(50);
            thread2.start();
            Thread.sleep(50);
            thread3.start();
        }catch (Exception e){

        }
    }

    //Synchronized issue
    // Fairness, Blocking , Interruptibility, Read/write locking
}
