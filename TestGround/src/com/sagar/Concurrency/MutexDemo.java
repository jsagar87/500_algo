package com.sagar.Concurrency;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Shopper extends  Thread{

    static int garlicCount = 0;
    static Lock pencil = new ReentrantLock();

    @Override
    public void run() {
        for(int i = 0 ; i < 5; i++) {
            pencil.lock();
            garlicCount++;
            pencil.unlock();

            System.out.println(Thread.currentThread().getName() + " is thinking.");
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}

public class MutexDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread sagar = new Shopper();
        Thread aditi = new Shopper();

        sagar.start();
        aditi.start();

        sagar.join();
        aditi.join();

        System.out.println(" total garlic to buy " + Shopper.garlicCount);

    }
}
