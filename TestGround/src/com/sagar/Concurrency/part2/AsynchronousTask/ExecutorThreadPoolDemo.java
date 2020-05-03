package com.sagar.Concurrency.part2.AsynchronousTask;

import java.util.concurrent.*;

//class VegetableChopper extends Thread {
//    @Override
//    public void run() {
//        System.out.println(Thread.currentThread().getName()+" chopped a vegetable!");
//    }
//}

public class ExecutorThreadPoolDemo {
    public static void main(String[] args) {
        // Lets check how many processors are available
        int numProcs = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(numProcs);
        for (int i =0; i<100; i++) {
            pool.submit(new VegetableChopper());
        }
        pool.shutdown();

    }
}
