package com.sagar.Concurrency.part2.AsynchronousTask;

class VegetableChopper extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" chopped a vegetable!");
    }
}
public class ThreadPoolDemo {
    public static void main(String[] args) {
        for (int i =0; i<100; i++) {
            new VegetableChopper().start();
        }

    }
}
