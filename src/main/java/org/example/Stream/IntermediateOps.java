package org.example.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class IntermediateOps {
    public static  void main(String[] args){
        //Intermediate operations transform a stream into another stream
        //They are lazy,meaning they don't execute until a terminal operation is invoked.

        List<String> list = Arrays.asList("Aksit","Ram","Shyam","Ghanshyam","Aryan");
        Stream<String> filteredStream = list.stream().filter(x->x.startsWith("A"));

        //no filtering at this point
        long res = list.stream().filter(x->x.startsWith("A")).count();
        System.out.println(res);


        //2.map
        Stream<String> stringStream = list.stream().map(String::toUpperCase);


        //3. sorted
        Stream<String> sortedStream =list.stream().sorted();
        Stream<String> sortedStreamUsingComprator = list.stream().sorted((a,b)-> a.length() - b.length());

        //4.distict
        System.out.println(list.stream().filter(x -> x.startsWith("A")).distinct().count());

        //5.limit

        System.out.println(Stream.iterate(1,x->x+1).skip(10).limit(100).count());

        //6.skip
        System.out.println(Stream.iterate(1,x->x+1).skip(10).limit(100).count());

    }
}
