package com.sagar.Leetcode.Concurrency.PrintOrder;

import java.lang.ref.SoftReference;

import static java.lang.System.out;

public class Foo {

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }


    public static void main(String[] args) {//throws InterruptedException {
        Print dv = new Print();
        out.println(dv.c);
        out.println(dv.i);
        out.println(dv.b);

//        SoftReference<Foo> foo = new SoftReference<>(new Foo());
//
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                out.println("Hello");
//            }
//        });
//
//        Foo F = new Foo();
//        Thread A = new Thread();
//        Thread B = new Thread();
//        Thread C = new Thread();
//        F = null;
//        A.start();
//        B.start();
//        C.start();
//
//        A.join();
//        B.join();
//        C.join();

    }
}