package org.example.multiThreading.executorService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllExample {
    //InvokeAll() and InvokeAny() in java and are used to execute multiple Callable tasks at once.

    //InvokeAll()
    //Executes all tasks,Waits until every task finishes, Returns a List of Future objects (one per task)
    //Blocking method (waits for all tasks)

    //invokeAny()
    //Starts all tasks
    //Returns result of the first task that completes
    //Cancels the remaining tasks

    //Blocking method
    //Returns only one result
    //Other tasks are cancelled

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<Integer> callable1 = () -> {
            Thread.sleep(1000);
            System.out.println("Task 1");
            return 1;
        };

        Callable<Integer> callable2 = () -> {
            Thread.sleep(1000);
            System.out.println("Task 2");
            return 2;
        };

        Callable<Integer> callable3 = () -> {
            Thread.sleep(1000);
            System.out.println("Task 3");
            return 3;
        };

        List<Callable<Integer>> list = Arrays.asList(callable1, callable2, callable3);

        List<Future<Integer>> futures = null;
        try {
            futures = executorService.invokeAll(list, 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {

        }

        for (Future<Integer> f : futures) {
            try {
                System.out.println(f.get());
            } catch (CancellationException e){

            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();
    }
}
