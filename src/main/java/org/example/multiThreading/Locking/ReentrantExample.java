package org.example.multiThreading.Locking;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class ReentrantExample {

    //Deadlock -> Two or more threads are stuck forever because each is waiting
    //for a resource held by the other.

//    Imagine two threads:
//
//    Thread 1 locks Resource A, then waits for Resource B
//    Thread 2 locks Resource B, then waits for Resource A
//
// Both keep waiting… forever


    //ReentrantLock is an advanced locking mechanism that gives more control
    //than synchronized and allows the same thread to lock multiple times safely
    //  same thread can lock again.

    //Methode
    //1. lock() - Acquires the lock, waits if needed.
    //2. lockInterruptibly() - Acquires the lock but can be interrupted while waiting,
    //3.tryLock() -> Tries to get the lock without waiting.
    //4. tryLock(time, unit) -> Waits for a specific time to get the lock.
    //5. unlock() -> Releases the lock.

    private final Lock lock = new ReentrantLock();

    public void outerMethod(){
       lock.lock();
       try{
           System.out.println("Outer method");
           innerMethod();
       }finally {
           lock.unlock();
       }
    }

    public void innerMethod(){
        lock.lock();
        try{
            System.out.println("Inner method");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();
        example.outerMethod();
    }
}
