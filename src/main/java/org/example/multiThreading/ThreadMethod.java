package org.example.multiThreading;

public class ThreadMethod extends Thread{///this is a User thread

    public ThreadMethod(String name){
        super(name);//we can set name for thread also
    }

    @Override
    public void run() { ///2.in run methode we are the actual code which I want to run.
        System.out.println("Thread is running....");

        for (int i=0;i<= 5;i++){
            try {
                Thread.sleep(1000); /// 5.Its stop the current thread execution for a specified period of time.
                System.out.println(Thread.currentThread().getName() + "-Priority:" + Thread.currentThread().getPriority() +" count"+ i);
                Thread.yield();///It's tell scheduler to give chance to another thread to execute. And the scheduler is free to ignore this hint.
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadMethod t1 = new ThreadMethod("vaibhaw");// Passing the thread name.
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start(); ///1. JVM by default run the run methode of the Thread.
        t1.join(); ///4. Thread will wait for completion of t1 thread...Here Main thread is waiting for T1 to complete the execution.

        ThreadMethod t2 = new ThreadMethod("low");
        ThreadMethod t3 = new ThreadMethod("medium");
        ThreadMethod t4 = new ThreadMethod("high");
        t2.setPriority(Thread.MIN_PRIORITY);
        t3.setPriority(Thread.NORM_PRIORITY);
        t4.setPriority(Thread.MAX_PRIORITY);
        t2.start();
        t3.start();
        t4.start();

        t4.interrupt(); ///6.Its tells thread to stop the execution whatever are you doing it do not matter.


        //Daemon thread -> It's run in background.JVM do not wait for daemon thread. ex - GC
        ThreadMethod t5 = new ThreadMethod("test");
        t5.start();
        t5.setDaemon(true);////JVM do not wait to complete this thread. If main thread complete the execution so program will not be execute any more and terminate the execution.

    }
}
