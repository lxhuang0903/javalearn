package com.yc.javalearn.java.queue;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * FileName: PriorityQueueDemo
 *
 * @author: yuchao
 * @date: 2018/9/17
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        int size = 9;
        System.out.println(size >>> 1);
        List<String> c = Arrays.asList("abc");
        PriorityQueue<String> queue = new PriorityQueue<>(c);
    }

}
