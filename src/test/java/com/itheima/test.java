package com.itheima;

import org.junit.Test;

public class test {
    @Test
    public void MaoPaoSort() {
        int[] arr = {1, 2, 3, 66, 7, 8, 99, 65};
        int[] ans = maopao(arr);
        for (int anss : ans) {
            System.out.println(anss);
        }

    }

    public int[] maopao(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {

            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
