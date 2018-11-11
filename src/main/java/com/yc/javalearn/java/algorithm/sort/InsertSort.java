package com.yc.javalearn.java.algorithm.sort;

/**
 * FileName: InsertSort
 *
 * @author: yuchao
 * @date: 2018/11/3
 */
public class InsertSort {
    public void sort(int[] array) {
        if (array == null) {
            throw new RuntimeException("array is null");
        }

        int length = array.length;
        if (length > 0) {
            for (int i = 1; i < length; i++) {
                int temp = array[i];
                int j = i;
                for (; j > 0 && array[j - 1] > temp; j--) {
                    array[j] = array[j - 1];
                }
                array[j] = temp;
            }
        }

    }
}
