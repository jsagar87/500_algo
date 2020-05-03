package com.sagar.Concurrency;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Cart extends Thread{

    static int garlicCount, potatoCount = 0;
    static Lock pencil = new ReentrantLock();

    @Override
    public void run() {
        for(int i = 0 ; i < 5; i++) {
            addGarlic();
            addPotato();

//            System.out.println(Thread.currentThread().getName() + " is thinking.");
//            try {
//                Thread.sleep(500);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
        }
    }

    private void addGarlic() {
        pencil.lock();
        garlicCount++;
        pencil.unlock();
    }

    private void addPotato() {
        pencil.lock();
        potatoCount++;
        pencil.unlock();
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread sagar = new Cart();
        Thread aditi = new Cart();

        sagar.start();
        aditi.start();

        sagar.join();
        aditi.join();

        System.out.println(" total garlic to buy " + Cart.garlicCount);
        System.out.println(" total potato to buy " + Cart.potatoCount);

    }
}

