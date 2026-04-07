package org.example.multiThreading;


//To create a new thread in java, you can either extend the Thread class or implement the Runnable interface.
public class MultiThreading2 {
    public static void main(String[] args) {
        System.out.println("Hello world");
        System.out.println(Thread.currentThread().getName());

        //A new class worMultithreading3 is created that extends Thread.The run methode is overridden to define the code that
        //constitutes the new thread. Start method is called to initiate the new thread.

        Multithreading3 world = new Multithreading3();
        world.start();


        //A new class MultiThreading4 is created that implements Runnable.
        //The run method is overridden to define the code that constitutes the new thread.
        //A thread Object is created by passing an instance of MyRunnable.
        //start method is called on the Thread object to initiate the new thread.


        ///** In both cases, the run method contains the code that will be executed in the new thread

        MultiThreading4 world1 = new MultiThreading4();
        Thread t1 = new Thread(world1);
        t1.start();


        for(int i=0;i<100000;i++){
            System.out.println("Hello");
        }
        

    }
}
