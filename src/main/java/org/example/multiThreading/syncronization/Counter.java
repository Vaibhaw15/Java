package org.example.multiThreading.syncronization;

public class Counter {

    private int count = 0;


    ///Race condition - A race condition is a situation where multiple thread access and modify shared data at the same time, and the final
    ///result depends on the unpredictable order of execution....we can remove reace condition by using scynchronized keyword.

   ///Critical section -> shared resources where access and modify happen that is called critical section.
   ///Synchronized keyword insure that when one thread is using this function so another thread have to wait
   /// Before that we are getting wrong output because in simultaneously both thread was reading the same value ond increasing on the same
   /// That is why we was getting wrong output.

   ///If you want like you do not want to make whole methode synchronized so you should add synchronized block inside the methode
   ///
 /*   public synchronized void increment(){
        count++;
    }

  */

    // Mutual exclusion - Only one thread is allowed to access a shared resource at a time.

   ///Here code synchronization between the block
   public void increment(){
       synchronized(this) {/// this -> means we are talking about one instance.ex -> if multiple thread is accessing increment methode so that time one thred only access this block.
           count++;
       }
   }

    public int getCount(){
       return count;
    }
}

