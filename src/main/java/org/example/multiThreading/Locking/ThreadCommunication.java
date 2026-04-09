package org.example.multiThreading.Locking;

public class ThreadCommunication {

    //Thead Communication is a mechanism where:
    // One Thread wait for a condition, and another thread notify it when that condition is met.

    //It is mainly used when one thread depends on the result of another thread

    //wait(), notify(), notifyAll()- These methods are used inside synchronized blocks.
    //wait() -> puts the thread into waiting state and releases the lock.
    //notify() -> wakes up one waiting thread
    //notifyAll() -> wakes up all waiting threads

    // It is required to avoid unnecessary CPU usage ans ensure proper synchronization between thread.



    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(new Producer(buffer), "Producer");
        Thread consumer = new Thread(new Consumer(buffer), "Consumer");

        producer.start();
        consumer.start();
    }



}

class SharedBuffer{
    private int data;
    private boolean hasData = false; //it indicates if buffer has data

    //producer method
    public synchronized void produce(int value){

        //if data already exists, wait until consumed
        while(hasData){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        //produce data
        data = value;
        System.out.println("Produced: "+data);

        hasData = true;
        notify(); //notify consumer that data is ready
    }

    public synchronized void consume(){

        //if no data, wait until produced

        while (!hasData){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        // Consume data
        System.out.println("Consumed: " + data);

        hasData = false;

        notify(); // notify producer that buffer is empty
    }
}


class Producer implements Runnable{
    private SharedBuffer buffer;

    public Producer(SharedBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public  void run(){
        int i=1;
        while (true){
            buffer.produce(i++);
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){}
        }
    }
}

class Consumer implements Runnable {
    private SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            buffer.consume(); // consume data
            try {
                Thread.sleep(500); // simulate delay
            } catch (InterruptedException e) {}
        }
    }
}

