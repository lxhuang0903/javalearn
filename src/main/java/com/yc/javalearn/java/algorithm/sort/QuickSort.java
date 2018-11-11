package com.yc.javalearn.java.algorithm.sort;

/**
 * FileName: QuickSort
 *
 * @author: yuchao
 * @date: 2018/11/3
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arrays = {5, 4, 9, 3, 8, 2, 1, 6, 7};
        new QuickSort().quickSort(arrays, 0, arrays.length -1);
        for (int i : arrays) {
            System.out.println(i);
        }
    }

    private void quickSort(int[] src, int begin, int end) {
        if (begin < end) {
            int key = src[begin];
            int i = begin;
            int j = end;
            while(i < j){
                while (i < j && src[j] > key) {
                    j--;
                }
                if (i < j) {
                    src[i] = src[j];
                    i++;
                }
                while (i < j && src[i] < key) {
                    i++;
                }
                if (i < j) {
                    src[j] = src[i];
                    j--;
                }
            }

            src[i] = key;

            quickSort(src, begin, i -1);
            quickSort(src, i+1, end);
        }
    }
}
