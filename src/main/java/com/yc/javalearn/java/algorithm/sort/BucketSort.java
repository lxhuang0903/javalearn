package com.yc.javalearn.java.algorithm.sort;

/**
 * FileName: BucketSort
 *
 * @author: yuchao
 * @date: 2018/11/3
 */
public class BucketSort {
    private int[] buckets;
    private int[] arrays;

    public BucketSort(int ranges, int[] arrays) {
        this.buckets = new int[++ranges];
        this.arrays = arrays;
    }

    private BucketSort sort() {
        if (arrays != null) {
            for (int i = 0; i < arrays.length; i++) {
                buckets[arrays[i]]++;
            }
        }
        return this;
    }

    private void print() {
        for (int i = buckets.length -1 ; i >= 0; i--) {
            for (int j = 0; buckets[i] > j ; j++) {
                System.out.println(i);
            }

        }
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{ 0,2,3,3,9,5,1,7,9};
        new BucketSort(9, arrays).sort().print();
    }
}
