package com.sagar.Concurrency;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicShopper extends  Thread {

    static AtomicInteger garlicCount = new AtomicInteger(0);

    @Override
    public void run() {
        for(int i = 0 ; i < 10000000; i++) {
            garlicCount.incrementAndGet();
        }
    }
}

public class AtomicVariableDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread sagar = new AtomicShopper();sagar.setName("sagar");
        Thread aditi = new AtomicShopper();aditi.setName("aditi");

        sagar.start();
        aditi.start();

        sagar.join();
        aditi.join();

        System.out.println(" total garlic to buy " + AtomicShopper.garlicCount.get());

    }
}
