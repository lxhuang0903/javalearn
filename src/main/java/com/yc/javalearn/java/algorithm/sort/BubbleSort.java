package com.yc.javalearn.java.algorithm.sort;

/**
 * FileName: BubbleSort
 *
 * @author: yuchao
 * @date: 2018/11/3
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] input = {3, 2, 7, 5, 0, 9};
        for (int i : new BubbleSort().sort1(input)) {
            System.out.println(i);
        }
    }

    private int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j]) {
                    int tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
        return array;
    }

    private int[] sort1(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;

                }
            }
        }
        return array;
    }
}
