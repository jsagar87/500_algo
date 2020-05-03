package com.sagar.Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    // Solution 2 using map with reverse lookup
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> keyToOldIdx = new HashMap();
        int fullLen = nums.length - 1;
        // Order of N iteration for mapping
        for ( int i = 0; i <= fullLen; i++) {
            keyToOldIdx.put(nums[i], i);
        }

        // Now lets sort
//        Arrays.sort(nums);

        int two_num[] = new int[2];

        for (int index = 0; index < fullLen; index++) {
            int key = target - nums[index];
            Integer altIndex = keyToOldIdx.get(key);
            if(null!=altIndex && altIndex!=index) {
                two_num[0] = index ;
                two_num[1] = altIndex ;
                break;
            }
//            int altIndex = Arrays.binarySearch(nums, key);
//            if (altIndex != -1) {
//                two_num[0] = (Integer)keyToOldIdx.get(index);
//                two_num[1] = (Integer)keyToOldIdx.get(altIndex);
//                break;
//            }
        }
        return two_num;
    }
//    Runtime: 2 ms, faster than 92.79% of Java online submissions for Two Sum.
//    Memory Usage: 42.4 MB, less than 5.65% of Java online submissions for Two Sum.


    // Solution 1 : Brutforce Way
//    public static int[] twoSum(int[] nums, int target) {
//        int two_num[] = new int[2];
//        int mid = nums.length/2; // 2
//        int fullLen = nums.length -1; // 3
//        for(int index = 0 ; index < fullLen; index++) {
//            int key = target - nums[index] ;
//            int altIndex = search(nums, key, index+1);
//            if ( altIndex != -1 ) {
//                two_num[0] = index;
//                two_num[1] = altIndex;
//                break;
//            }
//        }
//        return two_num;
//    }
//    public static int search(int[] nums, int key, int startIdx) {
//        int fullLen = nums.length - 1;
//        for (int i = startIdx ; i <= fullLen; i++ ){
//            if (key==nums[i])
//                return i;
//        }
//        return -1;
//    }
//    Runtime: 61 ms, faster than 8.79% of Java online submissions for Two Sum.
//    Memory Usage: 39.8 MB, less than 5.65% of Java online submissions for Two Sum.

    public static void main(String[] args) {

        // Case 1
        int[] inputArray = {2,7,11,15} ;
        int target = 9;
        int[] two = twoSum(inputArray, target);
        System.out.println(Arrays.toString(two));

        // Case 2
        int[] inputArray1 = {3,2,4} ;
        int target1 = 6;
        int[] two1 = twoSum(inputArray1, target1);
        System.out.println(Arrays.toString(two1));
    }
}
