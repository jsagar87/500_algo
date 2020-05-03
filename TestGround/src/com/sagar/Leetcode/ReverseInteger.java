package com.sagar.Leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int reverse(int x) {
        int sum=0;
        while(x != 0){
            if ( sum != (((sum * 10 + x % 10) - (x%10))/10) ) {
                return 0;
            }
            sum = sum * 10 + x % 10;
            x = x / 10;
        }
        return sum;
    }
}

public class ReverseInteger {
    public static void main(String[] args) throws IOException
    {
//        int y = -2147483648;
//        Integer yy = new Integer(y);
//        System.out.println(yy.toString());
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int x = Integer.parseInt(line);

            int ret = new Solution().reverse(x);

            String out = String.valueOf(ret);

            System.out.println(out);
        }
    }
}