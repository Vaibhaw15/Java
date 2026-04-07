package org.example.multiThreading;

public class MultiThreading1 {
    public static void main(String[] args) {
        //CPU : Brain of computer, is responsible for executing instructions from programs.
        //It performs basic arithmetic, logic, control and input/output operations specified by instructions.
        //Example - Intel core i7, AMD Ryzen 7

        //Core : A core is an individual processing unit within a CPU.Modern CPUs can have multiple cores
        //allowing them to perform multiple tasks simultaneously.

        //A quad - core processor has four cores, allowing it to perform four task simultaneously.
        //For instance, one core could handle your web browser, another your music player, another a download maneger,
        //and another a background system update.

        //program : A program is a set of instruction written in a programming language that tells the computer how to perform a
        //specific task. Example - Microsofr word is a program that allows users to create and edit documents.

        //Process : A process is an instance of a program that is being executed. When a program runs, the operating system
        //creates a process to manage its execution. Example - When we open Microsoft word, it becomes a process in the operating system.

        //Thread : A thread is the smallest unit of execution whitin a process. A Process can have multiple threads, which
        //shares the same resources but can run independently.
        // A web browser like google chrome might use multiple threads for different tabs, with each tab running as a separate thread.

        //Multitasking - Multitasking allows an operating system to run multiple process simultaneously. On single core cpu, this is done through
        //time sharing, rapidly switching between tasks. On multi - core CPUs, true parallel execution occurs,
        //with tasks distributed across cores. The OS scheduler balances the load, ensuring efficient and responsive system performance.
        //e.x..We are browsing the internet while listening to music and downloading a file.

        //Multitasking utilizes the capabilities of a cpu and its cores. When an operating system perform multitasking,
        //it can assign different tasks to different cores. This is more efficient that assigning all tasks to a single core.

        //Multithreading - A refers to the ability to execute multiple threads with a single process concurrently.
        //ex..A web browser can use multithreading by having separate threads for rendering the page, running javascript, and managing user inputes.This
        // makes the browser more responsive and efficient.

        //Multithreading enhances the efficiency of multitasking by breaking down individual tasks into smaller sub-tasks
        //or threads. These threads can be processed simultaneously, making better use of the CPU's capabilities.

        // In a single-core system - Both Threads and process are managed by the OS scheduler through time slicing and context switching to create the
        //illusion of simultaneous execution.

        // In a multi-core system: Both threads and process can run in parallel on different cores, with the OS
        //scheduler distributing tasks cores to optimize performance.

        //Time slicing - divides Cpu time into small intervals calls time slices or quanta.

        //Context Switching - Context switching is the process of saving the state of a currently running process or thread and
        //loading the state of the next one to be executed.

        //Multitasking can be achieved through multithreading where each task is divided into threads that are managed concurrently.

        //While multitasking typically refers to the running of multiple applications, multithreading is more granular, dealing
        //with multiple threads within the same application or process.


        // Java provides robust support for multithreading, allowing developers to creates applications that can perform multiple task simultaneously, improving performance
        // and responsiveness.

        //In java, multithreading is the concurrent execution of two or more threads to maximize the utilization of the CPU.
        //Java multithreading capabilities are part of the java.lang package, making it easy to implement concurrent execution.

        //In a single-core environment, Java's multithreading is managed by the JVM and the OS, which switch between threads to
        //give the illusion of concurrency.
        // The threads share the single core, and time-slicing is used to managed thread execution.

        //In a multi-core environment, Java multithreading can take full advantage of the available cores.
        //The JVM can distribute threads across multiple  cores, allowing true parallel execution of threads.


        //A thread is a lightweight process, the smallest unit of processing. Java supports multithreading through its
        //java.lang.Thread class and the java.lang.Runnable interface.

        //When a java program starts, one thread beings running immediately, which is called the main thread. This thread is responsible
        //for executing the main method of program.



    }
}
