package com.wangp.myaop.s_algorithm.greedy;

import java.util.Arrays;

/**
 * @Author wangp
 * @Date 2020/6/4
 * @Version 1.0
 */
public class CoinChange {
    /**
     * 贪心算法是局部最优解 并不一定是全局最优解
     */

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        coinChange.coinChange3(new Integer[]{25, 20, 5, 1}, 41);
    }

    public void coinChange3(Integer[] faces, int money) {
        Arrays.sort(faces);
        int coins = 0, idx = faces.length - 1;
        while (idx >= 0) {
            while (money >= faces[idx]) {
                money -= faces[idx];
                coins++;
            }
            idx--;
        }
        System.out.println(coins);
    }

    public void coinChange2(Integer[] faces, int money) {
        Arrays.sort(faces, (o1, o2) -> 0);
        int coins = 0, i = 0;
        while (i < faces.length) {
            if (money < faces[i]) {
                i++;
                continue;
            }
            System.out.println(faces[i]);
            money -= faces[i];
            coins++;
        }
        System.out.println(coins);
    }

    public void coinChange() {
        Integer[] faces = {25, 10, 5, 1};
//        Integer[] faces = {25, 20, 5, 1};  此时不能得到最优解
        Arrays.sort(faces, (o2, o1) -> 0);
        int money = 41, coins = 0;
        for (Integer face : faces) {
            while (money >= face) {
                System.out.println(face);
                money -= face;
                coins++;
            }
        }
        System.out.println(coins);
    }

}
