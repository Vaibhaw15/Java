package org.example.multiThreading.executorService;

import java.util.concurrent.*;

public class FutureMethodsExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        // ─────────────────────────────────────────────
        // 1. get() — Blocks until result is available
        // ─────────────────────────────────────────────
        Future<String> future1 = executor.submit(() -> {
            Thread.sleep(1000);
            return "Result from get()";
        });

        try {
            String result = future1.get(); // blocks here
            System.out.println("1. get(): " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // ─────────────────────────────────────────────
        // 2. get(timeout, TimeUnit) — Blocks with timeout
        // ─────────────────────────────────────────────
        Future<String> future2 = executor.submit(() -> {
            Thread.sleep(5000); // takes 5 seconds
            return "Result from get(timeout)";
        });

        try {
            String result = future2.get(2, TimeUnit.SECONDS); // only waits 2 sec
            System.out.println("2. get(timeout): " + result);
        } catch (TimeoutException e) {
            System.out.println("2. get(timeout): TimeoutException — task took too long!");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // ─────────────────────────────────────────────
        // 3. isDone() — Check if task is completed
        // ─────────────────────────────────────────────
        Future<String> future3 = executor.submit(() -> {
            Thread.sleep(1000);
            return "Result from isDone()";
        });

        System.out.println("3. isDone() before completion: " + future3.isDone()); // false

        try {
            future3.get(); // wait for it
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("3. isDone() after completion: " + future3.isDone()); // true

        // ─────────────────────────────────────────────
        // 4. cancel(mayInterruptIfRunning) — Cancel task
        // ─────────────────────────────────────────────
        Future<String> future4 = executor.submit(() -> {
            Thread.sleep(5000);
            return "Result from cancel()";
        });

        boolean cancelled = future4.cancel(true); // true = interrupt if running
        System.out.println("4. cancel(): Was cancelled? " + cancelled);
        System.out.println("4. isDone() after cancel: " + future4.isDone());       // true
        System.out.println("4. isCancelled() after cancel: " + future4.isCancelled()); // true

        // Trying to get() after cancel throws CancellationException
        try {
            future4.get();
        } catch (CancellationException e) {
            System.out.println("4. get() after cancel: CancellationException thrown!");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // ─────────────────────────────────────────────
        // 5. isCancelled() — Check if task was cancelled
        // ─────────────────────────────────────────────
        Future<String> future5 = executor.submit(() -> "Result from isCancelled()");

        try {
            future5.get(); // let it complete normally
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("5. isCancelled() (not cancelled): " + future5.isCancelled()); // false

        Future<String> future5b = executor.submit(() -> {
            Thread.sleep(5000);
            return "This will be cancelled";
        });
        future5b.cancel(true);
        System.out.println("5. isCancelled() (cancelled): " + future5b.isCancelled()); // true

        executor.shutdown();
    }
}
