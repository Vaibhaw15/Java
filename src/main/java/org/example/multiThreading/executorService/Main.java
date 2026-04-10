package org.example.multiThreading.executorService;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        //This is taking too many time
//      long startTime =  System.currentTimeMillis();
//      for(int i=0;i< 10;i++){
//          System.out.println(factorial(i));
//      }
//        System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
        /*              */
//        long startTime =  System.currentTimeMillis();
//        for(int i=0;i< 10;i++){
//            int finalI = i; // Variable used in lambda expression should be final or effectively final
//            Thread thread = new Thread(()->{
//                long result = factorial(finalI);
//                System.out.println(result);
//            });
//            thread.start();
//        }
//        System.out.println("Total time: " + (System.currentTimeMillis() - startTime)); // total time pahle hi dikha diya wait kiya hi nhi thread ko rukne ka


        //we are saving the time but the problem is we are creating the thread as well as we are also running the thread that is not good. so that's why executor service comes into picture.
        // problem is also we are also not reusing the thread
//        long startTime =  System.currentTimeMillis();
//        Thread[] threads = new Thread[9];
//        for(int i=1;i< 10;i++){
//            int finalI = i; // Variable used in lambda expression should be final or effectively final
//            threads[i-1] = new Thread(()->{
//                long result = factorial(finalI);
//                System.out.println(result);
//            });
//            threads[i-1].start();
//        }
//
//        for(Thread thread : threads){
//            try {
//                thread.join();// waiting for all thread to complete the execution
//            }catch (InterruptedException e){
//                Thread.currentThread().interrupt();
//            }
//        System.out.println("Total time: " + (System.currentTimeMillis() - startTime)); // total time pahle hi dikha diya wait kiya hi nhi thread ko rukne ka
//    }

        //Executor
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(3);// executor service reuse the thread. this is the benefit
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            executor.submit(() -> {
                long result = factorial(finalI);
                System.out.println(result);
            });
        }
        executor.shutdown(); // we have to shutdown executor service.. after shutdown we can not able to give task to executor using submit()
        try {
            //Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is interrupted, whichever happens first.
            executor.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total time: " + (System.currentTimeMillis() - startTime)); // total time pahle hi dikha diya wait kiya hi nhi thread ko rukne ka
   }

    private static long factorial(int n){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        long result = 1;
        for(int i=1;i<= n;i++){
            result  *= i;
        }
        return result;
    }
}

//definition
//Executor (Basic Interface)
//
//Executor is the simplest interface for running tasks asynchronously. void execute(Runnable command);
//Fire-and-forget (no result returned)
//No control over task lifecycle
//No shutdown mechanism
//Very minimal abstraction


//ExecutorService (Advanced Interface)
//
//ExecutorService extends Executor and provides full control over task execution and thread management.
//Submit tasks and get results
//Manage lifecycle (shutdown, termination)
//Handle multiple tasks efficiently

//execute()
//submit() (3 overloads) --> return future
//invokeAll() (2 overloads)
//invokeAny() (2 overloads)
//Lifecycle:
//shutdown()
//shutdownNow()
//isShutdown()
//isTerminated()
//awaitTermination()

//Think of ExecutorService as a task manager:
//
//Submit work → submit, execute
//Handle multiple tasks → invokeAll, invokeAny
//Control lifecycle → shutdown, awaitTermination
//Track results → Future


class Test{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // Future is an interface in java that represents the result of an asynchronous computations.
        //You submit a task (Callable)
        //The task runs in another thread
        //It will eventually return 42
        Future<Integer> future = executorService.submit(() -> 42);
        future.get();// we can block the execution.
        System.out.println(future.get());
        if(future.isDone()){
            System.out.println("Task is done !");
        }
        executorService.shutdown();


    }
}

