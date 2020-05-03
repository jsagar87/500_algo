package com.sagar.Concurrency;

class Shoppr extends  Thread {
    static int garlicCount = 0;
    private synchronized void addGarlic(){
        garlicCount++;
    }
    @Override
    public void run() {
        for(int i = 0 ; i < 10000000; i++) {
            addGarlic();
        }
    }
}

public class SynchronizeMethodDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread sagar = new Shoppr();sagar.setName("sagar");
        Thread aditi = new Shoppr();aditi.setName("aditi");
        sagar.start();
        aditi.start();
        sagar.join();
        aditi.join();
        System.out.println(" total garlic to buy " + Shoppr.garlicCount);
    }
}
