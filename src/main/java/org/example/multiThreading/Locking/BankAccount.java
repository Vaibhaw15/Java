package org.example.multiThreading.Locking;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private int balance = 100;


    // So here we was facing issue like if one thread is doing the work and taking time to complete it so anther thread had to wait it
    //That is a problem. so solve this by using Lock.

//    public synchronized void withdraw(int amount) {
//        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
//
//        if (balance >= amount) {
//            System.out.println(Thread.currentThread().getName() + " Proceeding with withdrawal");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//
//            }
//            balance -= amount;
//            System.out.println(Thread.currentThread().getName() + " complete withdrawal. Remaining balance: " + balance);
//
//        }else {
//            System.out.println(Thread.currentThread().getName() + " insufficient balance");
//        }
//    }


    //Lock is an interface
    //which thread acquire this lock can able to access critical section
    // methode -> lock.lock(),lock.unlock(),lock.trylock(),lock.trylock() with time
    // lock.lock() is like a syncronized it wait to acquire the lock

    private final Lock lock = new ReentrantLock();

    /// implementation of lock class

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);

//       if(lock.tryLock()){///Acquires the lock only if it is free at the time of invocation.It try to acquire immanently otherwise it go to else condition
//
//       }else {
//
//       }

        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {///Acquires the lock if it is free within the given waiting time and the current thread has not been interrupted If the lock is available this method returns immediately with the value true. If the lock is not available then the current thread becomes disabled for thread scheduling purposes
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Proceeding with withdrawal");
                        Thread.sleep(1000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " complete withdrawal. Remaining balance: " + balance);
                    } catch (Exception e) {
                        // Thread.currentThread().interrupt() is used to restore the interrupt flag after catching InterruptedException, ensuring proper thread behavior with ReentrantLock.
                            Thread.currentThread().interrupted();//interrupt() → sets/restores the interrupt flag
                    } finally {
                        lock.unlock();
                    }
                } else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance");

                }
            }else{
                System.out.println(Thread.currentThread().getName() + " Could not acquire the lock, will try later");
            }
        } catch (InterruptedException e) {
            //Thread.currentThread().interrupt() is used to restore the interrupt flag after catching InterruptedException, ensuring proper thread behavior with ReentrantLock.
           Thread.currentThread().interrupt();//interrupt() → sets/restores the interrupt flag
        }

        if(Thread.currentThread().isInterrupted()){
            System.out.println("clenup code and anothet which important after interrupt");
        }
    }
}
