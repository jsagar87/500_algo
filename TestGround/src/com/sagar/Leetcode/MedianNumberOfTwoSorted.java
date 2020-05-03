package com.sagar.Leetcode;

import java.util.Arrays;

class Solution1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLen = nums1.length + nums2.length;

        int mideanIndex;

        if ((totalLen%2)!=0) {
           mideanIndex =  (totalLen / 2) + 1;
        } else {
           mideanIndex =  (totalLen / 2) + 2;
        }

        int[] collect = new int[mideanIndex];
        int i=0,j =0,k=0;
        int flag=-1;

        for ( ; i < mideanIndex  ; ) {
            if (nums1[j] < nums2[k]) {
                collect[i++] = nums1[j++];
            } else {
                collect[i++] = nums2[k++];
            }

            if(j>=nums1.length){
                flag=0;
                break;
            } else if(k>=nums2.length){
                flag = 1;
                break;
            }
        }

        while(i<mideanIndex){
            if(flag == 0){
                collect[i++] = nums2[k++];
            }
            else
                collect[i++] = nums1[j++];
        }

        for(int z : collect){
            System.out.println(z);
        }

        if ( totalLen%2 != 0 ) {
            return collect[mideanIndex-1];
        } else {
            return (collect[mideanIndex-1]+collect[mideanIndex-2])/2.0 ;
        }
    }
}
public class MedianNumberOfTwoSorted {
    public static void main(String[] args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};

        Solution1 sln = new Solution1();
        double ans = sln.findMedianSortedArrays(nums1, nums2);
        System.out.println("Answ is : " + ans);
        assert ans == 8.5;

    }
}
