package org.example.Stream;

import java.util.stream.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        //feature introduced in java 8
        //process collections of data in a functional and declarative manner
        //simplify Data Processing
        //Embrace Functional Programming
        //Imprve Readability and Maintainability
        //Enable Easy Parallelism

        //What is stream
        //A sequence of elements supporting functional and declarative programming

        //How to use Stream?
        //Source -> Intermediate Operation -> Terminal Operation


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//        int count =0;
//        for(int i : numbers){
//            if(i % 2 == 0){
//                count++;
//            }
//            System.out.println(count);
//        }

        System.out.println(numbers.stream().filter(x->x%2 == 0).count());

// Creating Streams

// 1. From Collections
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream1 = list.stream();

// 2. From Arrays
        String[] array = {"a", "b", "c"};
        Stream<String> stream2 = Arrays.stream(array);

// 3. Using Stream.of()
        Stream<String> stream3 = Stream.of("a", "b");

// 4. Infinite Streams
        Stream<Integer> generateStream = Stream.generate(() -> 1);

// iterate with limit (to avoid infinite loop)
        List<Integer> numbers1 = Stream
                .iterate(1, x -> x + 1)
                .limit(100)
                .collect(Collectors.toList());


    }

}
