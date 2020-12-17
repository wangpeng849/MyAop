package com.wangp.myaop.leetcode.middle;

import java.util.Arrays;

/**
 * <pre>
 * classname CloseStrings
 * description
 *
 *
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
 *
 * 操作 1：交换任意两个 现有 字符。
 * 例如，abcde -> aecdb
 * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
 * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
 * 你可以根据需要对任意一个字符串多次使用这两种操作。
 *
 * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
 * </pre>
 *
 * @author wangpeng
 * @date 2020/11/17 20:38
 **/
public class CloseStrings {

    public boolean closeStrings(String word1, String word2) {
        int len = word1.length();
        if (len != word2.length()) {
            return false;
        }
        int[] list1 = new int[26];
        int[] list2 = new int[26];
        for (int i = 0; i < len; i++) {
            list1[word1.charAt(i) - 'a']++;
            list2[word2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if ((list1[i] ^ list2[i]) == 1) {
                return false;
            }
        }
        Arrays.sort(list1);
        Arrays.sort(list2);
        for (int i = 0; i < 26; i++) {
            if (list1[i] != list2[i]) {
                return false;
            }
        }
        return true;
    }


}