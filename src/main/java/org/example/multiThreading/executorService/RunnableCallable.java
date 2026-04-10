package org.example.multiThreading.executorService;

import java.util.concurrent.*;

public class RunnableCallable {
    //Runnable is the older interface used to execute a task without returning a result;
    //void run();
    //execute()
    Runnable task = () -> {
        System.out.println("Running task");
    };

    // No return, can not return checked exceptions

    //Callable is a newer interface that returns a results and can throw exception
    //V call() throws Exception;
    //submit()

    Callable<Integer> task1 = ()->{
        return  42;
    };

    // Returns a value (V)
    //Can throw checked exceptions, Used with Future
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // Runnable
        executor.execute(()->System.out.println("No result"));

        //Callable
        Future<Integer> future = executor.submit(()->100);

        //1. submit(Runnable task)
        //Future<?> submit(Runnable task)
        // its run the task and can not result value but still returns Future
        Future<?> f = executor.submit(() -> {
            System.out.println("Hello");
        });
        f.get(); // null return karega

        //2. <T> Future<T> submit(Runnable task, T result);
        // it run Runnable and we can manually give result

        Future<String> f1 = executor.submit(()->{
            System.out.println("Task done");
        },"Success");
        f1.get(); // "Success"

        //submit(Callable<T> task)
        //<T> Future<T> submit(Callable<T> task);
        //It run callable and it return actual result

        Future<Integer> f2 = executor.submit(() -> {
            return 10 + 20;
        });
        f2.get(); // 30

        executor.shutdown();// It can not accept new task.Kaam lena band , jo chal raha hai wo finish krta hai
        executor.shutdownNow();// It can not accept new task.Kaam lena band ,Running tasks ko interrupt karne ki koshish karta hai
        executor.awaitTermination(5, TimeUnit.SECONDS);//Wait karta hai jab tak executor completely band na ho jaye.Usually shutdown() ke baad use hota hai
    }


}
