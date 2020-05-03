package com.sagar.Leetcode;

public class ConsecutiveSequence {
    public int lengthOfLongestSubstring(String s) {
        int max_len = 1;
        int start_ptr = 0;

        int cur_len = 1;
        int cur_ptr = 0;
        for (int i = 0; i <s.length() - 1; i++) {
            if (s.charAt(i+1) - s.charAt(i) != 0) {
                cur_len++;
                if (cur_len > max_len) {
                    max_len = cur_len;
                    start_ptr = cur_ptr;
                }
                continue;
            }

            cur_len = 1;
            cur_ptr = i;

        }
        return max_len;
    }
    public static void main(String[] args) {

    }
}
