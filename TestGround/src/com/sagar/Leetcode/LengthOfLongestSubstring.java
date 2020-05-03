package com.sagar.Leetcode;

import java.util.HashMap;
import java.util.List;

public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character,Character> map = new HashMap<>();
        int max_len = 0;
        int start_ptr = 0;

        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i),s.charAt(i));
                if (map.size() > max_len) {
                    max_len = map.size();
                }
                continue;
            }else {
                map.clear();
                map.put(s.charAt(i),s.charAt(i));
            }
        }
        return max_len;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lols = new LengthOfLongestSubstring();
        String test1_str = "abcabcbb";
        int test1_expected = 3;
        int test1_actual = LengthOfLongestSubstring.lengthOfLongestSubstring(test1_str);
        printTestResult("Test 1", test1_str,test1_expected,test1_actual);

        String test2_str = "bbbbb";
        int test2_expected = 1;
        int test2_actual = LengthOfLongestSubstring.lengthOfLongestSubstring(test2_str);
        printTestResult("Test 2", test2_str, test2_expected, test2_actual);

        String test3_str = "pwwkew";
        int test3_expected = 3;
        int test3_actual = LengthOfLongestSubstring.lengthOfLongestSubstring(test3_str);
        printTestResult("Test 3", test3_str, test3_expected, test3_actual);

        String test4_str = "aab";
        int test4_expected = 2;
        int test4_actual = LengthOfLongestSubstring.lengthOfLongestSubstring(test4_str);
        printTestResult("Test 4", test4_str, test4_expected, test4_actual);
    }

    public static void printTestResult(String testName,
                                String test_str,
                                int test_expected_result,
                                int test_actual_result) {
        System.out.println(testName + " " + (test_expected_result == test_actual_result ? "Passes " : "Fails " ) + " for test string " + test_str  + " actual : " + test_actual_result + " expected : " + test_expected_result);
    }
}
