package com.sagar.priorityqueue;

import java.util.Iterator;

public class MaxPQ<Key extends Comparable<Key>> implements Iterable<Key>{

    /***************************
     *  Internal data structure
     ***************************/

    private int N;
    private Key[] pq;

    /**********************
     *  Public API methods
     **********************/

    //  PQ API 1 : Create a priority queue of initial capacity max
    public MaxPQ(int max){
        this.pq = (Key[])new Comparable[max];
        N = 0;
    }

    // PQ API 2 : Insert a key into the priority queue
    public void insert(Key v){
        pq[N++] = v;
        swim(N);
    }

    // PQ API 3 : Return the largest key
    public Key max(){
        return pq[1];
    }

    // PQ API 4 : return and remove the largest key
    public Key delMax(){
        Key del = max();
        exch(pq,1,N);
        pq[N--] = null;
        sink(1);
        return del;
    }

    // PQ API 5 : is the priority queue empty?
    public boolean isEmpty() {
        return N < 2;
    }

    // PQ API 6 : number of keys in the priority queue
    public int size(){
        return N;
    }

    private void swim(int k){
        while( k>1 && less(pq,k/2, k) ) {
            exch(pq, k/2, k);
            k = k/2;
        }
    }

    /*********************
     *  Private methods
     *********************/

    private void sink(int k){
        while (2*k <= N) { // Child position is less then max index, then only
            int j = 2*k;   // First Child of k is at 2k
            if (j < N && less(pq, j, j+1)) j++; // select largest one
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    private boolean less(Key[] a,int i,int j){
        return a[i].compareTo(a[j]) < 1 ;
    }

    private void exch(Key[] a,int i,int j){
        Key temp = a[i] ;
        a[i] = a[j] ;
        a[j] = temp;
    }

    @Override
    public Iterator<Key> iterator() {
        return null;
    }
}
