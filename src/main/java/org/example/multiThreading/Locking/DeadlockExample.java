package org.example.multiThreading.Locking;

public class DeadlockExample {

    public static void main(String[] args) {



    // //Deadlock ->  Deadlock is a situation in multithreading where two or more threads
    //are blocked forever, waiting for each other to release a resources. This typically occure
    //when two or more thread have circular dependencies on a set of locks.

     //We avoid deadlock by ensuring thread to get lock as a consistent manner.same sequence

    Pen pen = new Pen();
    Paper paper = new Paper();

    Thread thread1 =  new Thread(new Task1(pen, paper),"Thread-1");
    Thread thread2 =  new Thread(new Task2(pen, paper),"Thread-2");
    thread1.start();
    thread2.start();

    }

}

class Pen{
    public synchronized void writeWithPenAndPaper(Paper paper){
        System.out.println(Thread.currentThread().getName() + " is using pen "+ this + "trying to use paper");
        paper.finishWriting();
    }

    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName() + " finished using pen "+ this);
    }
}
class Paper{
    public synchronized void writeWithPaperAndPen(Pen pen){
        System.out.println(Thread.currentThread().getName() + " is using paper "+ this + "trying to use pen");
        pen.finishWriting();
    }

    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName() + " finished using paper "+ this);
    }
}

class  Task1 implements Runnable{

    private Pen pen;
    private  Paper paper;

    public  Task1(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); //thread2 lock paper and tries to lock pen
    }
}

class  Task2 implements Runnable{

    private Pen pen;
    private  Paper paper;

    public  Task2(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
 //  paper.writeWithPaperAndPen(pen);
        synchronized (pen) {// here we are ensuring that give me first lock of pen then you will request the lock of paper.
            paper.writeWithPaperAndPen(pen); //thread2 lock paper and tries to lock pen
        }
    }
}
