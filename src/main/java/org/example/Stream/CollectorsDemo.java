package org.example.Stream;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsDemo {
    public static void main(String[] args) {


    //Collectors is a utility class
    //provides a set of methods to create common collectors

    //1. Colllecting to a List
    List<String> names = Arrays.asList("Alice","Bob","Charlie");
    List<String> res = names.stream()
            .filter(name -> name.startsWith("A"))
            .collect(Collectors.toList());

    System.out.println(res);

    //2. Collecting to a set
    List<Integer> nums = Arrays.asList(1,2,3,4,4,5);
    Set<Integer> set = nums.stream().collect(Collectors.toSet());
    System.out.println(set);

    //3.Collecting to a specific Collection
     ArrayDeque<String> collect = names.stream().collect(Collectors.toCollection(()-> new ArrayDeque<>()));

     //4. Joining Strings
     String concatenatedNames = names.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
     System.out.println(concatenatedNames);

     //5. Summarizing Data
     //Generate statistical summary( count, sum, min, average, max)

      List<Integer> numbers = Arrays.asList(2,3,5,7,11);
      IntSummaryStatistics stats = numbers.stream().collect(Collectors.summarizingInt(x->x));
      System.out.println("Count: " + stats.getCount());
      System.out.println("Sum" + stats.getSum());
      System.out.println("Min: "+ stats.getMin());
      System.out.println("Average: "+ stats.getAverage());
      System.out.println("Max: "+ stats.getMax());

        //6. Calculating Averages
        Double average = numbers.stream().collect(Collectors.averagingInt(x->x));
        System.out.println(average);

        //7. Counting Elements
        Long count = numbers.stream().collect(Collectors.counting());
        System.out.println("Count" + count);

        //8. Grouping Elements
        List<String> words = Arrays.asList("hello", "world","java", "streams", "collecting");
        System.out.println(words.stream().collect(Collectors.groupingBy(x->x.length())));
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length,Collectors.joining(", "))));
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length,Collectors.counting())));

        TreeMap<Integer,Long> treeMap = words.stream().collect(Collectors.groupingBy(String::length,TreeMap::new,Collectors.counting()));
        System.out.println(treeMap);



    }

}
