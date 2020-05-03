package com.sagar.Concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {

    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 5000;
    private int sushiEatCount = 0;

    public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    @Override
    public void run() {
        while ( sushiCount > 0 ) {

            // pick up chopsticks
            this.firstChopstick.lock();
            this.secondChopstick.lock();
            if ( sushiCount >0 ) {
                sushiCount--;
                sushiEatCount++;

                System.out.println(this.getName() + " is having sushi number " + sushiCount);
//                try {
//                    Thread.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            this.secondChopstick.unlock();
            this.firstChopstick.unlock();
        }
        System.out.println(this.getName() + " had " + sushiEatCount);
    }
}
public class DeadlockDemo {
    public static void main(String[] args) throws InterruptedException {

        Lock stickA = new ReentrantLock();
        Lock stickB = new ReentrantLock();
        Lock stickC = new ReentrantLock();

        Thread T1 = new Philosopher("Barron", stickA, stickB);
        Thread T2 = new Philosopher("Olivia", stickB, stickC);
        Thread T3 = new Philosopher("Steve", stickA, stickC);

        T1.start();
        T2.start();
        T3.start();

        T1.join();
        T2.join();
        T3.join();
    }
}
