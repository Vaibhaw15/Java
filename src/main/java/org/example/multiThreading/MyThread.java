package org.example.multiThreading;

public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("RUNNING");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();//New
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
//      System.out.println(Thread.currentThread().getState());
        Thread.sleep(100);
        System.out.println(t1.getState());
        t1.join();// Main methode is waiting for t1 to get finished. Means Joins are waiting for another methode to get finished.
        System.out.println(t1.getState());
    }


}
