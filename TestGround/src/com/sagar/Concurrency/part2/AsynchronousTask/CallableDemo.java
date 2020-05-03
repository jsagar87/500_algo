package com.sagar.Concurrency.part2.AsynchronousTask;


import java.util.concurrent.*;

class HowManyVegeables implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Olivia is counting vegetables");
        Thread.sleep(3000);
        return 42;
    }
}
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Future<Integer> res = exec.submit(new HowManyVegeables());
        try {
            System.out.println(res.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        exec.shutdown();
    }


}
