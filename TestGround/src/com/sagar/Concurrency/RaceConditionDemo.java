package com.sagar.Concurrency;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShopperRaceCondition extends Thread {
    public static int bagsOfChips = 1;
    private static Lock pencil = new ReentrantLock();
    public ShopperRaceCondition(String name) {
        this.setName(name);
    }
    @Override
    public void run() {
        if (this.getName().contains("Olivia")) {
            pencil.lock();
            try {
                bagsOfChips += 3;
            } finally {
                pencil.unlock();
            }
        } else { // for barron's thread
            pencil.lock();
            try {
                bagsOfChips *= 2;
            } finally {
                pencil.unlock();
            }
        }


    }
}
public class RaceConditionDemo {


    public static void main(String[] args) {
        ShopperRaceCondition[] shoppers = new ShopperRaceCondition[10];
        for ( int i = 0; i<shoppers.length/2; i++) {
            shoppers[2*i] = new ShopperRaceCondition("Olivia-"+i);
            shoppers[2*i+1] = new ShopperRaceCondition("Barron-"+i);
        }

    }
}
