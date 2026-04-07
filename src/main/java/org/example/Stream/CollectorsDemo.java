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


        //9. partitioning Elements
        // Pratitions elements into two groups (true and false) based on a predicates
        System.out.println(words.stream().collect(Collectors.partitioningBy(x -> x.length() > 5)));


        //10. Mapping and Collecting
        // Applies a mapping function before collecting

        System.out.println(words.stream().collect(Collectors.mapping(x->x.toUpperCase(),Collectors.toList())));


        //Example 1. Collecting Names by length
        List<String> l1 = Arrays.asList("Anna","Bob","Alexander","Brian","Alice");
        System.out.println(l1.stream().collect(Collectors.groupingBy(String::length)));

        //Example 2. Counting word Occurrences
        String sentance = "hello world hello java world";
        System.out.println(Arrays.stream(sentance.split("")).collect(Collectors.groupingBy(x->x,Collectors.counting())));

        //Example 3
        List<Integer> l2 = Arrays.asList(1,2,3,4,5,6,7);
        System.out.println(l2.stream().collect(Collectors.partitioningBy(x->x % 2 == 0)));

        //Example 4: Summing Values in a map
        Map<String,Integer> items = new HashMap<>();
        items.put("Apple",10);
        items.put("Banana",20);
        items.put("Orange",15);
        System.out.println(items.values().stream().reduce(Integer::sum).get());
        System.out.println(items.values().stream().collect(Collectors.summingInt(x-> x)));


        //Example 5: Creating a Map from Stream Elements
        List<String> fruits = Arrays.asList("Apple","Banana","Cherry");
        System.out.println(fruits.stream().collect(Collectors.toMap(x->x.toUpperCase(),x-> x.length())));




    }

}
