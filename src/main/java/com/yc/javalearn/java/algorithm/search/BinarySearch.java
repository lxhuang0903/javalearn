package com.yc.javalearn.java.algorithm.search;

import java.awt.geom.Ellipse2D;

/**
 * FileName: BinarySearch
 *
 * @author: yuchao
 * @date: 2018/11/4
 */
public class BinarySearch {
    private int[] array;

    public BinarySearch(int[] array) {
        this.array = array;
    }

    public int searchRecursion(int target) {
        if (array != null) {
            return searchRecursion(target, 0, array.length - 1);
        }
        return -1;
    }

    private int searchRecursion(int target, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = start + (end - start) / 2;

        if (array[mid] == target) {
            return mid;
        } else if (target < array[mid]) {
            return searchRecursion(target, start, mid - 1);
        } else {
            return searchRecursion(target, mid + 1, end);
        }



    }

}
