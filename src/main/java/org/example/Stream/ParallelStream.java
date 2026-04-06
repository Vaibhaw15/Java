package org.example.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ParallelStream {
    public static void main(String[] args) {
        //A type of stream that enable parallel processing of element
        //Allowing multiple threads to process parts of the stream simultaneously
        //This can significantly improve performance for large data sets
        //workload is distributed across multiple threads

        long startTime = System.currentTimeMillis();
        List<Integer> list = Stream.iterate(1,x->x+1).limit(2000).toList();
        List<Long> factorialsList = list.stream().map(ParallelStream::factorial).toList();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken with sequential stream: " + (endTime-startTime) + " ms");


         startTime = System.currentTimeMillis();
         list = Stream.iterate(1,x->x+1).limit(2000).toList();
         factorialsList = list.parallelStream().map(ParallelStream::factorial).toList();
         //factorialsList = list.parallelStream().map(ParallelStream::factorial).sequential().toList();
         endTime = System.currentTimeMillis();
        System.out.println("Time taken with parallel stream: " + (endTime-startTime) + " ms");

        //Parallel stream are most effective for cpu-intensive or large dataset where tasks are independent
        //They may add overhead for simple tasks or small datasets

        //Cumulative Sum
        //[1,2,3,4,5] --> [1,3,6,10,15]

        List<Integer> number = Arrays.asList(1,2,3,4,5);
        AtomicInteger sum = new AtomicInteger(0);
        List<Integer> cumulativeSum = number.parallelStream().map(sum::addAndGet).toList();
        //it gives wrong output because parallel stream doesn't depend on sequential manner. That's why it's gives wrong output
        //We Use parallel stream where I need independent operation
        System.out.println("Expected cumulative sum:[1,3,6,10,15]");
        System.out.println("Actual result with parallel stream "+ cumulativeSum);

    }

    private static long factorial(int n){
        long result = 1;
        for(int i=2;i<= n;i++)
        {
            result *= i;
        }
        return result;
    }
}
