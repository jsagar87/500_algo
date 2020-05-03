package com.sagar.Leetcode;

import javax.sound.midi.SysexMessage;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class AddTwoNumbers {

    static class ListNode {
       int val;
       ListNode next;
        ListNode(int x) { val = x; }
    }

    public static Queue<Integer> listNodeToQueue(ListNode l) {
        Queue<Integer> numQueue = new ArrayDeque<>();

        while(l != null) {
            numQueue.add(l.val);
            l = l.next;
        }
        return numQueue;
    }
//    public static int stackToNum(Stack<Integer> numStack) {
//        int num = 0;
//        int lenNum = numStack.size()-1;
//        while(!numStack.empty()) {
//            int numChar = numStack.pop();
//            double decimalN = numChar *  Math.pow(10, lenNum);
//            num += decimalN ;
//            lenNum--;
//        }
//        return num;
//    }
    public static ListNode listToSumList(Queue<Integer> numStack1, Queue<Integer> numStack2) {
        ListNode finalnumList = null;

        int lenNum1 = numStack1.size()-1;
        int lenNum2 = numStack2.size()-1;
        int carry = 0;

        Queue<Integer> longer = null;
        Queue<Integer> shorter = null;
        if (lenNum1>lenNum2) {
            longer = numStack1;
            shorter = numStack2;
        }
        else {
            longer = numStack2;
            shorter = numStack1;
        }
            ListNode runner = null;
            while(!shorter.isEmpty()) {
                Integer s1Digit = longer.poll();//9
                Integer s2Digit = shorter.poll();//9
                Integer sum = s1Digit + s2Digit + carry;
                int digitTolist = sum.toString().charAt(sum.toString().length()) - 48;
                carry = sum.toString().charAt(sum.toString().length()-1) - 48;
                ListNode newNode = new ListNode(digitTolist);
                if (finalnumList == null) {
                    finalnumList = newNode;
                    runner = newNode;
                } else {
                    ListNode old = runner;
                    runner = newNode;
                    old.next = newNode;
                }
            }
            while(!longer.isEmpty()) {
                Integer s1Digit = longer.poll();//9
                Integer sum = s1Digit + carry;
                int digitTolist = sum.toString().charAt(sum.toString().length())-48;
                carry = sum.toString().charAt(sum.toString().length()-1)-48;
                ListNode newNode = new ListNode(digitTolist);
                if (finalnumList == null) {
                    finalnumList = newNode;
                    runner = newNode;
                } else {
                    ListNode old = runner;
                    runner = newNode;
                    old.next = newNode;
                }
            }

            if(carry>0) {
            ListNode newNode = new ListNode(carry);
            if (finalnumList == null) {
                finalnumList = newNode;
            } else {
                ListNode old = runner;
                old.next = newNode;
            }
        }
        return finalnumList;
    }
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode result = null;
        int up = 0;
        ListNode p = result;
        while (p1 != null || p2 != null) {
            int val1 = p1 != null ? p1.val : 0;
            int val2 = p2 != null ? p2.val : 0;
            int sum = val1 + val2 + up;
            boolean isIn = sum > 9;
            if (isIn) {
                sum = sum - 10;
            }
            ListNode curr = new ListNode(sum);
            if (result != null) {
                p.next = curr;
                p = p.next;
            } else {
                result = curr;
                p = result;
            }
            up = isIn ? 1 : 0;
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        if (up > 0) {
            p.next = new ListNode(up);
        }
        return result;
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        Queue<Integer> num1Stack = listNodeToQueue(l1);
        Queue<Integer> num2Stack = listNodeToQueue(l2);
        return listToSumList(num1Stack, num2Stack);
        // So far C = N + N = 2N

//        int num1 = stackToNum(num1Stack);
//        int num2 = stackToNum(num2Stack);
//
//        // C = 2N
//        // C = C + c(stackToNum)*2 = C + N * 2 = 2N + 2N = 4N
//        System.out.println("Final integer " + num1);
//        System.out.println("Final integer " + num2);
//
//        int num3 = num1 + num2 ;
//        ListNode resultNode = numToReversedLinkedList(num3);
//
//        return resultNode;
    }

//    private static ListNode numToReversedLinkedList(Integer num) {
//        ListNode reverseLinkRep = null;
//        char[] number = num.toString().toCharArray();
//        // 807
//        for (char c : number) {
//            ListNode node = new ListNode(c-48);
//            System.out.println(node.val);
//            if(null == reverseLinkRep ) {
//                reverseLinkRep = node;
//            } else {
//                ListNode temp = reverseLinkRep;
//                reverseLinkRep = node;
//                reverseLinkRep.next = temp;
//            }
//        }
//        return reverseLinkRep;
//
//    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
//        ListNode l2 = new ListNode(4);
//        ListNode l3 = new ListNode(3);
//        l1.next = l2;
//        l2.next = l3;


//[1,9,9,9,9,9,9,9,9,9]

        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(9);
        ListNode l6 = new ListNode(9);
        ListNode l7 = new ListNode(9);
        ListNode l8 = new ListNode(9);
        ListNode l9 = new ListNode(9);
        ListNode l10 = new ListNode(9);
        ListNode l11 = new ListNode(9);
        ListNode l12 = new ListNode(9);
        ListNode l13 = new ListNode(9);
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;
        l9.next = l10;
        l10.next = l11;
        l11.next = l12;
        l12.next = l13;

        ListNode ans = addTwoNumbers(l1,l4);
        while(ans!=null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }
}
