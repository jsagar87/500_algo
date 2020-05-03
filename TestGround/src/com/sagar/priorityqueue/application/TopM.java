package com.sagar.priorityqueue.application;

/************************************************************************
 * Compilation:    javac TopM.java
 * Execution:      java TopM m < input.txt
 * Dependencies:   MinPQ.java Transaction.java StdIn.java StdOut.java
 *
 * Given an integer m from the command line and an input stream where
 * each line contains a String and a long value, this MinPQ client
 * prints the m lines whose numbers are the highest.
 *
 * % java TomM 5 < tinyBatch.txt
 *  Thompson    2/27/2000  4747.08
 *  vonNeumann  2/12/1994  4732.35
 *  vonNeumann  1/11/1999  4409.74
 *  Hoare       8/18/1992  4381.21
 *  vonNeumann  3/26/2002  4121.85
 *
 *************************************************************************/

import com.sagar.priorityqueue.MaxPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 */
public class TopM {

    // This class should not be instantiated,
    // hence making the default constructor private
    private TopM() { }

    public static void main(String []args) {
        int m = Integer.parseInt(args[0]);
        StdOut.println(args[0]);

        MaxPQ<Transaction> pq = new MaxPQ<>(m+1);

        while(StdIn.hasNextLine()){
            String line = StdIn.readLine();
            Transaction transaction = new Transaction(line);

            pq.insert(transaction);
            if(pq.size()>m)
                pq.delMax();
        }
        Stack<Transaction> stack = new Stack<>();
        for(Transaction transaction : pq){
            stack.push(transaction);
        }
        for(Transaction transaction : stack){
            StdOut.println(transaction);
        }
    }
}
