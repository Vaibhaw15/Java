package org.example.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TerminalOperation {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3);

        //1. collect
        list.stream().skip(1).collect(Collectors.toList());

        //2.forEach
        list.stream().forEach(x->System.out.println(x));

        //3.reduce : combine elements to produce a single result
        Optional<Integer> optionalInteger = list.stream().reduce(Integer::sum);
        System.out.println(optionalInteger.get());

        //4.count

        //5.anyMatch , allMatch, nonMatch

        boolean b = list.stream().anyMatch(x -> x % 2 == 0);
        System.out.println(b);


        boolean b1 = list.stream().allMatch(x -> x > 0);
        System.out.println(b1);

        boolean b3 = list.stream().noneMatch(x -> x < 0);
        System.out.println(b1);

        // 6. findFirst, findAny
        System.out.println(list.stream().findFirst().get());
        System.out.println(list.stream().findAny().get());

        //Example Filtering and Collecting Names
        List<String> names = Arrays.asList("Anna","Bob","Charlie","David");
        System.out.println(names.stream().filter(x->x.length() > 3).collect(Collectors.toList()));

        //Example Squaring and sorting Numbers
        List<Integer> numbers = Arrays.asList(1,2,3,4,6,8,5);
        System.out.println(numbers.stream().map(x->x*x).sorted().toList());

        //Example :Summing Values
        List<Integer> integers = Arrays.asList(1,2,3,4,5,5);
        System.out.println(integers.stream().reduce(Integer::sum).get());




    }
}
