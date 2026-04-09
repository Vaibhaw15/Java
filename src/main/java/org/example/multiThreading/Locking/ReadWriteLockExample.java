package org.example.multiThreading.Locking;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    //Multiple threads to read at the same time, but only one thread to write at a time.

    private int count = 0;

    private final ReadWriteLock lock= new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock(); //  use for reading

    private final Lock writeLock = lock.writeLock(); // use for writing

    public void increment(){
        writeLock.lock(); // Multiple lock can acquire that read lock. Condition -> Tabhi acquire kr payega jb write lock acquire nhi kiya gya ho
        try{
            count++;
        }finally {
            writeLock.unlock();
        }
    }

    public int getCount(){
        readLock.lock();
        try{
            return count;
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample counter = new ReadWriteLockExample();

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName() + " read: " + counter.getCount());
                }
            }
        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    counter.increment();
                    System.out.println(Thread.currentThread().getName() + " increment ");
                }
            }
        };

        Thread writeThread = new Thread(writeTask);
        Thread readThread1 = new Thread(readTask);
        Thread readThread2 = new Thread(readTask);

        writeThread.start();
        readThread1.start();
        readThread2.start();

        writeThread.join(); // waiting here to complete the thread
        readThread1.join();
        readThread2.join();

        System.out.println("Final count: " + counter.getCount());


    }

}
