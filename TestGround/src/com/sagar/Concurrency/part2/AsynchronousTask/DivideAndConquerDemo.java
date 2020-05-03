package com.sagar.Concurrency.part2.AsynchronousTask;

import java.util.concurrent.*;


class RecursiveSum extends RecursiveTask<Long> {

    private long lo, hi;

    public RecursiveSum(long lo, long hi) {
        this.lo = lo;
        this.hi = hi;
    }
    @Override
    protected Long compute() {
        if (hi-lo<=100000) {
            long total = 0;
            for (long i = lo ; i <= hi; i++) {
                total +=i ;

            }
            return total;
        }
        else {
            long mid = (hi+lo)/2;
            RecursiveSum left = new RecursiveSum(lo, mid);
            RecursiveSum right = new RecursiveSum(mid+1,hi);
            left.fork();
            return right.compute() + left.join();
        }

    }
}

// Divide and conquer using fork and join framework
// Framework for executing recursive, divide-and-conquer
// work with multiple processors
public class DivideAndConquerDemo {

    public static void main(String[] args) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Long total = pool.invoke(new RecursiveSum(0,1000000000));
        pool.shutdown();
        System.out.println("Total sum "+total);

    }
}
