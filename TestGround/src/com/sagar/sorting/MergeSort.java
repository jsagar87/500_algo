package com.sagar.sorting;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int m[] = {6,5,4,3,2,1};
        print("Unsorted : ", m);
        int len = m.length;
        int lo = 0, hi = len-1;
        int aux[] = new int[len];
        sort (m,aux,lo,hi);
        print("Sorted : ", m);
    }

    public static void print(String prefix, int []m) {
        System.out.println(prefix);
        Arrays.stream(m).forEach(value -> System.out.print(value + ", "));
        System.out.println(" ");
    }
    public static void sort(int []m, int []aux, int lo, int hi ) {
        int len = m.length ;
        int mid = lo + (hi - lo) / 2 ;


        // Copy all to aux
        for (int i=0; i<len; i++)
            aux[i] = m[i];

        // First split
        divide (m, aux, 0, len/2);         // Left half
        divide (m, aux, len/2+1, len-1);   // Right half
        merge  (m, aux, 0,len);
    }
    public static void divide(int []m, int []aux, int lo, int hi ) {

        int len = hi - lo;
        if (len <= 1)
            return;
        int mid = lo + (hi - lo)/2 ;
        divide (m, aux, lo, mid );             //  Left half
        divide (m, aux, mid+1, len-1); //  Right half

        merge (m,aux,lo,hi);
    }
    public static void merge(int []m, int []aux, int lo, int hi) {
        if (m.length<=1) return;

        int mid = lo + (hi -lo)/2;
        int i = lo, j = mid + 1;

        for (int k = lo; k < hi; k++) {
            if ( i > mid ) aux[k] = m[j] ; // Copy right half since left pointer done
            if ( j > hi ) aux[k] = m[i] ;  // Copy remaining left half since right half is done

            // Core logic
            if (m[i]<m[j]) aux[k] = m[i++];
            else           aux[k] = m[j++];
        }
    }

}
