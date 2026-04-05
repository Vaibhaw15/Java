package org.example.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class JavaFirst {
    public static void main(String[] args) {

        //Stream

        //Java 8 --> minimal code, functional programming
        //java 8 -->  lambda expression, Stream, Date & time api

        //lambda expression
        //lambda expression is an anonymous function(mo name, no return type, no access modifier)
        //lambda expression is used with functional interface

        Thread t1 = new Thread(() -> {
            System.out.println("Hello");
        });

        MathOperation sumOperation = (a, b) -> a + b;
        MathOperation minusOperation = (a, b) -> a - b;

        int res = sumOperation.operation(3, 5);
        System.out.println(res);


        //Predicate --> is a functional interface which has only one abstract methode. Predicate is a boolean value function
        // test(T t)
        // It hold only condition.We are storing conditon in variable
        Predicate<Integer> predicate = x -> x % 2 == 0;
        System.out.println(predicate.test(4));

        Predicate<String> isWordStartWithA = x -> x.startsWith("A");
        System.out.println(isWordStartWithA.test("Ankit"));

        Predicate<String> isWordEndingWithA = x -> x.endsWith("A");
        System.out.println(isWordStartWithA.test("Ankita"));

        Predicate<String> and = isWordStartWithA.and(isWordEndingWithA);
        System.out.println(and.test("Ankit"));


        //Function --> It work for you
        Function<Integer,Integer> doubleInt = x-> x*2;
        Function<Integer,Integer> tripleInt = x-> x*3;
        System.out.println(doubleInt.andThen(tripleInt).apply(20)); //andThen and and is a default methode
        System.out.println(doubleInt.apply(100));
        System.out.println(doubleInt.compose(tripleInt).apply(200));
        Function<Integer,Integer> identity  = Function.identity();
        System.out.println(identity.apply(5));

        //Consumer - It take and can not return anything. It use
        Consumer<Integer>  print = x->System.out.println(x);
        print.accept(50);


        List<Integer> list =  Arrays.asList(1,2,3);

        Consumer<List<Integer>> printList = x -> {
            for(int i : x){
                System.out.println(i);
            }
        };

        printList.accept(list);

        //Supplier --> It doesn't take anything but it return somthing
        Supplier<String> giveHelloWorld = ()-> "Hello World";
        System.out.println(giveHelloWorld.get());



        //Combined example
        Predicate<Integer> predicate1 = x -> x % 2==0;
        Function<Integer,Integer> function = x-> x * x;
        Consumer<Integer> consumer = x -> System.out.println(x);
        Supplier<Integer> supplier = ()-> 100;

        if(predicate1.test(supplier.get())){
            consumer.accept(function.apply(supplier.get()));
        }

        //Bipredicate,Biconsumer,BiFunction -- take two input

        BiPredicate<Integer,Integer> isSumEven = (x,y) -> (x+y) %2 == 0;
        System.out.println(isSumEven.test(5,5));

        BiConsumer<Integer,Integer> biConsumer = (x,y) -> {
            System.out.println(x);
            System.out.println(y);
        };

        BiFunction<String,String,Integer> biFunction = (x,y)->(x+y).length();
        System.out.println(biFunction.apply("a","bc"));



        Function<Integer,Integer> a = x-> 2 * x;
        //Unary and binary come for reduce <Integer,Integer>. all work is same
        UnaryOperator<Integer> b = x->2*x;
        BinaryOperator<Integer> c = (x,y) -> x+y;

        //Methode reference --> use methode without invoking and in place of lambda expression
        List<String> students = Arrays.asList("Ram","Shayam","sita");
        students.forEach(System.out::println); // students.forEach(x-> System.out.println(x)); ----we can use methode reference instead of lambda

        //Constructor reference
        List<String> name = Arrays.asList("A","B","C");
        name.stream().map(MobilePhone::new).collect(Collectors.toList());




    }

}

class MobilePhone{
    String name;
    MobilePhone(String name){
        this.name = name;
    }
}


//earlier we use this way when we do not have lambda expression. refer -- line no 14
class Task implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello");
    }
}


//Earlier, we used this approach because we didn’t have lambda expressions. The problem was that we had to create separate operations for add,
// delete, and multiply, which was quite difficult. So, we converted it to use lambda expressions.
// check line no --> 19
class SumOperation implements MathOperation{

    @Override
    public int operation(int a, int b) {
        return a+b;
    }
}


interface MathOperation{
    int operation(int a, int b);
}





