package com.sagar.Concurrency;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicShopperSynch extends  Thread {

    static Integer garlicCount = 0;

    @Override
    public void run() {
        for(int i = 0 ; i < 10000000; i++) {
//            synchronized (garlicCount){  // Used with : vstatic Integer garlicCount = 0;
//            synchronized (this){
            synchronized (AtomicShopperSynch.class){
            garlicCount
                    ++;
            }
        }
    }
}

public class SynchronizedStatementDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread sagar = new AtomicShopperSynch();sagar.setName("sagar");
        Thread aditi = new AtomicShopperSynch();aditi.setName("aditi");
        sagar.start();
        aditi.start();
        sagar.join();
        aditi.join();
        System.out.println(" total garlic to buy " + AtomicShopperSynch.garlicCount);

    }
}
