package com.wangp.myaop.leetcode;

/**
 * @author wangpeng
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x, ListNode node) {
        val = x;
        next = node;
    }

    public ListNode(int x) {
        val = x;
    }

    public void print() {
        System.out.println(val);
        while (next != null) {
            System.out.println(next.val);
            next = next.next;
        }
    }
}